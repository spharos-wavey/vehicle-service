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
import xyz.wavey.vehicleservice.vo.ResponseVehicle;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final FrameRepo frameRepo;
    private final BillitaZoneRepo billitaZoneRepo;

    @Override
    public ResponseEntity<Object> addVehicle(RequestVehicle requestVehicle) {
        String smartkeyId = UUID.randomUUID().toString();
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
            //todo  스마트키는 UUID로 진행될거같아서 수정요소가 보임, uuid 2023-05-03 신현채
            .smartKey(smartkeyId)
            .frame(frameRepo.findById(requestVehicle.getFrameId()).get())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @Override
    public ResponseVehicle getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(() -> new ServiceException("error"));

        BillitaZone billitaZone = billitaZoneRepo.findById(vehicle.getLastZone()).get();

        //todo res빌리타존 추가해서 반환값 정하기
        return ResponseVehicle.builder()
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
            .frameId(vehicle.getFrame().getId())
            .washTime(vehicle.getWashTime())
            .place(billitaZone)
            .build();

    }
}
