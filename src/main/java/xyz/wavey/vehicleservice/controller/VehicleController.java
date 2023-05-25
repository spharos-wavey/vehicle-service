package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.service.VehicleService;
import xyz.wavey.vehicleservice.vo.RequestVehicle;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping()
    public ResponseEntity<Object> addVehicle(@RequestBody RequestVehicle requestVehicle) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(vehicleService.addVehicle(requestVehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(vehicleService.getVehicle(id));
    }

    @GetMapping("/billitazone")
    public ResponseEntity<Object> getVehicleInBillitaZone(
        @RequestParam("id") String id,
        @RequestParam("sDate") String sDate,
        @RequestParam("eDate") String eDate) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(vehicleService.getVehicleInBillitaZone(Long.valueOf(id), sDate, eDate));
    }

}
