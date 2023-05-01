package xyz.wavey.vehicleservice.vehicle.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.vehicle.model.Vehicle;
import xyz.wavey.vehicleservice.vehicle.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vehicle.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vehicle.vo.ResponseVehicle;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepo vehicleRepo;
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
            .smartKey(requestVehicle.getSmartKey())
        .build());
    return ResponseEntity.status(HttpStatus.OK).body(vehicle);
  }

//  @Override
//  public ResponseVehicle getVehicleById(Long id) {
//    Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(() -> new ServiceException("error"));
//    return ResponseVehicle.builder()
//        .color(vehicle.getColor())
//        .feature(vehicle.getFeature())
//        .number(vehicle.getNumber())
//        .latitude(vehicle.getLatitude())
//        .longitude(vehicle.getLongitude())
//        .available(vehicle.getAvailable())
//        .charge(vehicle.getCharge())
//        .image(vehicle.getImage())
//        .lastZone(vehicle.getLastZone())
//        .smartKey(vehicle.getSmartKey())
//        .build();
//
//  }
}
