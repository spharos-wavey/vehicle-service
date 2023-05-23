package xyz.wavey.vehicleservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.model.BookList;
import xyz.wavey.vehicleservice.repository.*;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;
import java.util.UUID;

import xyz.wavey.vehicleservice.vo.ResponseGetVehicleInBillitaZone;
import xyz.wavey.vehicleservice.vo.ReviewInfoMapping;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final FrameRepo frameRepo;
    private final BillitaZoneRepo billitaZoneRepo;
    private final ReviewRepo reviewRepo;
    private final BookListRepo bookListRepo;

    @Override
    public ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle) {
        Vehicle vehicle = vehicleRepo.save(Vehicle.builder()
            .feature(requestVehicle.getFeature())
            .number(requestVehicle.getNumber())
            .latitude(requestVehicle.getLatitude())
            .longitude(requestVehicle.getLongitude())
            .available(requestVehicle.getAvailable())
            .charge(requestVehicle.getCharge())
            .lastZone(billitaZoneRepo.findById(requestVehicle.getLastZone())
                    .orElseThrow(() ->
                            new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(), NOT_FOUND_BILLITAZONE.getHttpStatus())))
            .washTime(requestVehicle.getWashTime())
            .smartKey(UUID.randomUUID().toString())
            .frame(frameRepo.findById(requestVehicle.getFrameId()).orElseThrow(()
                -> new ServiceException(NOT_FOUND_FRAME.getMessage(),
                NOT_FOUND_FRAME.getHttpStatus())))
            .mileage(requestVehicle.getMileage())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @Override
    public ResponseGetVehicle getVehicle(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
                NOT_FOUND_VEHICLE.getHttpStatus()));

        BillitaZone billitaZone = vehicle.getLastZone();

        List<ReviewInfoMapping> reviewList = reviewRepo.findAllByVehicleId(id);

        return ResponseGetVehicle.builder()
            .feature(vehicle.getFeature())
            .number(vehicle.getNumber())
            .latitude(vehicle.getLatitude())
            .longitude(vehicle.getLongitude())
            .available(vehicle.getAvailable())
            .charge(vehicle.getCharge())
            .lastZone(vehicle.getLastZone().getId())
            .smartKey(vehicle.getSmartKey())
            .frameInfo(vehicle.getFrame())
            .washTime(vehicle.getWashTime())
            .place(billitaZone)
            .mileage(vehicle.getMileage())
            .review(reviewList)
            .build();

    }

    @Override
    public List<ResponseGetVehicleInBillitaZone> getVehicleInBillitaZone(Long id, String sDate, String eDate) {
        List<ResponseGetVehicleInBillitaZone> returnValue = new ArrayList<>();

        //todo 중복코드에 대한 해결법 구상 후 적용 (2023-05-21) - 김지욱
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(sDate, dateTimeFormatter);
            endDate = LocalDateTime.parse(eDate, dateTimeFormatter);
        } catch (Exception e) {
            throw new ServiceException(BAD_REQUEST_DATEFORMAT.getMessage(), BAD_REQUEST_DATEFORMAT.getHttpStatus());
        }

        List<Vehicle> vehicleList = vehicleRepo.findAllByLastZone(billitaZoneRepo.findById(id).orElseThrow(() ->
                new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(), NOT_FOUND_BILLITAZONE.getHttpStatus())));

        for (Vehicle vehicle : vehicleList) {
            // 해당 차량의 모든 예약내용을 조회한다.
            List<BookList> bookLists = bookListRepo.findAllByVehicleIdOrderByStartDate(vehicle.getId());
            boolean canBook = true;
            for (BookList bookList : bookLists) {
                // 예약 테이블에서 예약 시작시간을 기준으로 오름차순 정렬했으므로 예약 시작시간이 현재 요청으로 들어온 예약 종료시간 보다 뒤에 있는 경우 비교를 하지 않아도 됨
                if (bookList.getStartDate().isAfter(endDate)) {
                    break;
                }

                if (bookList.getEndDate().isAfter(startDate) && endDate.isAfter(bookList.getStartDate())) {
                    canBook = false;
                    break;
                }
            }
            returnValue.add(ResponseGetVehicleInBillitaZone.builder()
                    .carName(vehicle.getFrame().getCarName())
                    .vehicleId(vehicle.getId())
                    .canBook(canBook && vehicle.getAvailable())
                    .defaultPrice(vehicle.getFrame().getDefaultPrice())
                    .vehicleImage(vehicle.getFrame().getImage())
                    .distancePrice(vehicle.getFrame().getDistancePrice())
                    .currentCharge(vehicle.getCharge())
                    .build());
        }
        return returnValue;
    }
}
