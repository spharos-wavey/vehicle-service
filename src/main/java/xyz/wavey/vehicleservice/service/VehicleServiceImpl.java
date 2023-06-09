package xyz.wavey.vehicleservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.BillitaZone;
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

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    @Transactional(readOnly = false)
    public Vehicle addVehicle(RequestVehicle requestVehicle) {
        return vehicleRepo.save(Vehicle.builder()
            .feature(requestVehicle.getFeature())
            .number(requestVehicle.getNumber())
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
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseGetVehicle getVehicle(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
                NOT_FOUND_VEHICLE.getHttpStatus()));

        BillitaZone billitaZone = vehicle.getLastZone();

        List<ReviewInfoMapping> reviewList = reviewRepo.findAllByVehicleId(id);

        return ResponseGetVehicle.builder()
            .feature(vehicle.getFeature())
            .number(vehicle.getNumber())
            .available(vehicle.getAvailable())
            .charge(vehicle.getCharge())
            .actualReturnedZone(vehicle.getLastZone().getId())
            .smartKey(vehicle.getSmartKey())
            .frameInfo(vehicle.getFrame())
            .washTime(vehicle.getWashTime())
            .place(billitaZone)
            .mileage(vehicle.getMileage())
            .review(reviewList)
            .build();

    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGetVehicleInBillitaZone> getVehicleInBillitaZone(Long id, String sDate, String eDate) {
        List<ResponseGetVehicleInBillitaZone> returnValue = new ArrayList<>();

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
            boolean canBook = bookListRepo.timeFilter(vehicle.getId(), startDate, endDate).isEmpty();
            returnValue.add(ResponseGetVehicleInBillitaZone.builder()
                    .carName(vehicle.getFrame().getCarName())
                    .carBrandName(vehicle.getFrame().getCarBrand().getBrandName())
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

    @Override
    @Transactional(readOnly = true)
    public boolean timeFilter(Long id, String sDate, String eDate) {
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(sDate, dateTimeFormatter);
            endDate = LocalDateTime.parse(eDate, dateTimeFormatter);
        } catch (Exception e) {
            throw new ServiceException(BAD_REQUEST_DATEFORMAT.getMessage(), BAD_REQUEST_DATEFORMAT.getHttpStatus());
        }
        return !bookListRepo.existsByVehicleIdAndEndDateIsAfterAndStartDateIsBefore(id, startDate, endDate);
    }
}
