package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.service.VehicleService;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicle;
import xyz.wavey.vehicleservice.vo.ResponseGetVehicleInBillitaZone;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping()
    public ResponseEntity<Object> addVehicle(@RequestBody RequestVehicle requestVehicle) {
        return vehicleService.addVehicle(requestVehicle);
    }

    @GetMapping("/{id}")
    public ResponseGetVehicle getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getVehicleInBillitaZone(@RequestParam("billitazone") String id) {
        List<ResponseGetVehicleInBillitaZone> responseGetVehicleInBillitaZoneList = new ArrayList<>();
        responseGetVehicleInBillitaZoneList.add(ResponseGetVehicleInBillitaZone.builder()
                        .vehicleModel("테스트 모델 1")
                        .vehicleId(1L)
                        .canBook(true)
                        .defaultPrice(27000)
                        .vehicleImage("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                        .distancePrice(100)
                        .currentCharge(77)
                .build());
        responseGetVehicleInBillitaZoneList.add(ResponseGetVehicleInBillitaZone.builder()
                .vehicleModel("테스트 모델 2")
                .vehicleId(2L)
                .canBook(true)
                .defaultPrice(24000)
                .vehicleImage("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                .distancePrice(100)
                .currentCharge(60)
                .build());
        responseGetVehicleInBillitaZoneList.add(ResponseGetVehicleInBillitaZone.builder()
                .vehicleModel("테스트 모델 3")
                .vehicleId(3L)
                .canBook(false)
                .defaultPrice(20000)
                .vehicleImage("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                .distancePrice(100)
                .currentCharge(77)
                .build());

        return ResponseEntity.status(HttpStatus.OK).body(responseGetVehicleInBillitaZoneList);
    }

}
