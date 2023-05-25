package xyz.wavey.vehicleservice.service;

import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicleInBillitaZone;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(RequestVehicle requestVehicle);

    ResponseGetVehicle getVehicle(Long id);

    List<ResponseGetVehicleInBillitaZone> getVehicleInBillitaZone(Long id, String sDate, String eDate);
}
