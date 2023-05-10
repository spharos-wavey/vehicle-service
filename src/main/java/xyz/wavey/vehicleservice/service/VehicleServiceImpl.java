package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.BillitaZone;
import xyz.wavey.vehicleservice.repository.BillitaZoneRepo;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final FrameRepo frameRepo;
    private final BillitaZoneRepo billitaZoneRepo;

    @Override
    public ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle) {
        Vehicle vehicle = vehicleRepo.save(Vehicle.builder()
            .color(requestVehicle.getColor())
            .feature(requestVehicle.getFeature())
            .number(requestVehicle.getNumber())
            .latitude(requestVehicle.getLatitude())
            .longitude(requestVehicle.getLongitude())
            .available(requestVehicle.getAvailable())
            .charge(requestVehicle.getCharge())
            .image(requestVehicle.getImage())
            .lastZone(requestVehicle.getLastZone())
            .washTime(requestVehicle.getWashTime())
            .smartKey(UUID.randomUUID().toString())
            .frame(frameRepo.findById(requestVehicle.getFrameId()).get())
            .mileage(requestVehicle.getMileage())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @Override
    public ResponseGetVehicle getVehicle(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(() -> new ServiceException("error"));

        //todo Optional.get 에러코드 추가하기
        BillitaZone billitaZone = billitaZoneRepo.findById(vehicle.getLastZone()).get();

        return ResponseGetVehicle.builder()
            .color(vehicle.getColor())
            .feature(vehicle.getFeature())
            .number(vehicle.getNumber())
            .latitude(vehicle.getLatitude())
            .longitude(vehicle.getLongitude())
            .available(vehicle.getAvailable())
            .charge(vehicle.getCharge())
            .image(vehicle.getImage())
            .lastZone(vehicle.getLastZone())
            .smartKey(vehicle.getSmartKey())
            .frameInfo(vehicle.getFrame())
            .washTime(vehicle.getWashTime())
            .place(billitaZone)
            .mileage(vehicle.getMileage())
            .build();

    }
}
