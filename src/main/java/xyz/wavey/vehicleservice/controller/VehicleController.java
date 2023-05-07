package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.VehicleService;
import xyz.wavey.vehicleservice.vo.RequestVehicle;
import xyz.wavey.vehicleservice.vo.ResponseVehicle;

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
    public ResponseVehicle getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

}
