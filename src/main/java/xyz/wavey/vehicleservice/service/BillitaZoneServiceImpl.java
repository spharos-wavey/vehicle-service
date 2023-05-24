package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.repository.BookListRepo;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.vo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import xyz.wavey.vehicleservice.repository.VehicleRepo;

import java.util.List;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.model.BookList;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillitaZoneServiceImpl implements BillitaZoneService {

    private final BillitaZoneRepo billitaZoneRepo;
    private final VehicleRepo vehicleRepo;
    private final BookListRepo bookListRepo;

    public ResponseEntity<Object> addBillitaZone(RequestBillitaZone requestBillitaZone) {
        BillitaZone billitaZone = billitaZoneRepo.save(BillitaZone.builder()
            .name(requestBillitaZone.getName())
            .latitude(requestBillitaZone.getLatitude())
            .longitude(requestBillitaZone.getLongitude())
            .zoneAddress(requestBillitaZone.getZoneAddress())
            .build());
        return ResponseEntity.status(HttpStatus.OK).body(billitaZone);
    }

    public ResponseBillitaZone getBillitaZone(Long id) {
        BillitaZone billitaZone = billitaZoneRepo.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(),
                NOT_FOUND_BILLITAZONE.getHttpStatus()));
        return ResponseBillitaZone.builder()
            .latitude(billitaZone.getLatitude())
            .longitude(billitaZone.getLongitude())
            .name(billitaZone.getName())
            .zoneAddress(billitaZone.getZoneAddress())
            .build();
    }

    @Override
    public ResponseEntity<Object> getAllBillitaZone() {
        List<ResponseGetAllBillitaZone> returnValue = new ArrayList<>();
        List<BillitaZone> billitaZoneList = billitaZoneRepo.findAll();

        for (BillitaZone billitaZone : billitaZoneList) {
            returnValue.add(ResponseGetAllBillitaZone.builder()
                .id(billitaZone.getId())
                .name(billitaZone.getName())
                .latitude(billitaZone.getLatitude())
                .longitude(billitaZone.getLongitude())
                .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @Override
    public ResponseEntity<Object> timeFilter(String sDate, String eDate, double lat, double lng) {

        List<ResponseTimeFilter> returnValue = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(sDate, dateTimeFormatter);
            endDate = LocalDateTime.parse(eDate, dateTimeFormatter);
        } catch (Exception e) {
            throw new ServiceException(BAD_REQUEST_DATEFORMAT.getMessage(), BAD_REQUEST_DATEFORMAT.getHttpStatus());
        }

        for (BillitaZone billitaZone : billitaZoneInLimitDistance(lat, lng)) {
            int rentAbleAmount = 0;
            List<Vehicle> vehiclesInBillitaZone = vehicleRepo.findAllByLastZone(billitaZone);
            for (Vehicle vehicle : vehiclesInBillitaZone) {
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
                if (canBook) {
                    rentAbleAmount++;
                }
            }

            returnValue.add(ResponseTimeFilter.builder()
                    .billitaZoneId(billitaZone.getId())
                    .billitaZoneLat(billitaZone.getLatitude().doubleValue())
                    .billitaZoneLng(billitaZone.getLongitude().doubleValue())
                    .rentAbleAmount(rentAbleAmount)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }


    @Override
    public List<BillitaZone> billitaZoneInLimitDistance(double lat, double lng) {
        List<BillitaZone> returnValue = new ArrayList<>();

        List<BillitaZone> billitaZoneList = billitaZoneRepo.findAll();
        for (BillitaZone billitaZone : billitaZoneList) {
            double theta = lat - billitaZone.getLatitude().doubleValue();
            double dist = Math.sin(lat * Math.PI / 180.0)
                    * Math.sin(billitaZone.getLatitude().doubleValue() * Math.PI / 180.0)
                    + Math.cos(lat * Math.PI / 180.0)
                    * Math.cos(billitaZone.getLatitude().doubleValue() * Math.PI / 180.0)
                    * Math.cos(theta * Math.PI / 180.0);
            dist = Math.acos(dist);
            dist = dist * 180 / Math.PI;
            dist *= 60 * 1.1515 * 1609.344;

            // 10km
            if (dist < 10000) {
                returnValue.add(billitaZone);
            }
        }

        return returnValue;
    }

    @Override
    public List<ResponseGetNowBillita> getNowBillita(double lat, double lng) {
        List<ResponseGetNowBillita> returnValue = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime twoHoursLater = currentTime.plusHours(2);
        // todo 반환되는 자동차 선정 기준 필요 - 2023/05/22 - 김지욱
        // 주어진 위경도로부터 반경 10km 이내에 있는 모든 빌리타존 내의 차량을 그대로 반환하는데 모든 값을 반환하는 것은 비효율적인것 같음
        // 데이터 생성할 때 분산해서 빌리타존별로 차량을 최대 5개만 넣는 식으로 해결해야할 듯
        for (BillitaZone billitaZone : billitaZoneInLimitDistance(lat,lng)) {
            List<Vehicle> vehiclesInBillitaZone = vehicleRepo.findAllByLastZone(billitaZone);
            for (Vehicle vehicle : vehiclesInBillitaZone) {
                boolean canBook = true;
                List<BookList> bookLists = bookListRepo.findAllByVehicleIdOrderByStartDate(vehicle.getId());
                for(BookList bookList : bookLists){
                    if(bookList.getStartDate().isBefore(twoHoursLater) && bookList.getEndDate().isAfter(currentTime)){
                        canBook = false;
                        break;
                    }
                }
                if(canBook) {
                    returnValue.add(ResponseGetNowBillita.builder()
                        .vehicleId(vehicle.getId())
                        .billitaZoneId(vehicle.getLastZone().getId())
                        .billitaZoneName(vehicle.getLastZone().getName())
                        .carBrand(vehicle.getFrame().getCarBrand().getBrandName())
                        .carName(vehicle.getFrame().getCarName())
                        .carImage(vehicle.getFrame().getImage())
                        .build());
                }
            }
            log.info(returnValue.toString());
        }

        return returnValue;
    }
}
