package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_BILLITAZONE;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_FRAME;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_VEHICLE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.ReviewRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;
import java.util.UUID;
import xyz.wavey.vehicleservice.vo.ReviewInfoMapping;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final FrameRepo frameRepo;
    private final BillitaZoneRepo billitaZoneRepo;
    private final ReviewRepo reviewRepo;

    @Override
    public ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle) {
        Vehicle vehicle = vehicleRepo.save(Vehicle.builder()
            .feature(requestVehicle.getFeature())
            .number(requestVehicle.getNumber())
            .latitude(requestVehicle.getLatitude())
            .longitude(requestVehicle.getLongitude())
            .available(requestVehicle.getAvailable())
            .charge(requestVehicle.getCharge())
            .lastZone(requestVehicle.getLastZone())
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

        BillitaZone billitaZone = billitaZoneRepo.findById(vehicle.getLastZone()).orElseThrow(
            () -> new ServiceException(NOT_FOUND_BILLITAZONE.getMessage(),
                NOT_FOUND_BILLITAZONE.getHttpStatus()));

        List<ReviewInfoMapping> reviewList = reviewRepo.findAllByVehicleId(id);

        return ResponseGetVehicle.builder()
            .feature(vehicle.getFeature())
            .number(vehicle.getNumber())
            .latitude(vehicle.getLatitude())
            .longitude(vehicle.getLongitude())
            .available(vehicle.getAvailable())
            .charge(vehicle.getCharge())
            .lastZone(vehicle.getLastZone())
            .smartKey(vehicle.getSmartKey())
            .frameInfo(vehicle.getFrame())
            .washTime(vehicle.getWashTime())
            .place(billitaZone)
            .mileage(vehicle.getMileage())
            .review(reviewList)
            .build();

    }
}
