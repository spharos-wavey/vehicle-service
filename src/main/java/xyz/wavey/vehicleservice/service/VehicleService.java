package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicleInBillitaZone;

import java.util.List;

public interface VehicleService {
    ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle);

    ResponseGetVehicle getVehicle(Long id);

    List<ResponseGetVehicleInBillitaZone> getVehicleInBillitaZone(Long id, String sDate, String eDate);
}
