package xyz.wavey.vehicleservice.vehicle.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vehicle.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vehicle.vo.ResponseVehicle;

public interface VehicleService {
  ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle);

  ResponseVehicle getVehicleById(Long id);
}
