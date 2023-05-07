package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseVehicle;

public interface VehicleService {
    ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle);

    ResponseVehicle getVehicleById(Long id);
}
