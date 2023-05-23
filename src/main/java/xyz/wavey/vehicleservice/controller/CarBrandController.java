package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.CarBrandService;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByCarBrand;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carbrand")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @PostMapping()
    public ResponseEntity<Object> addCarBrand(@RequestBody RequestCarBrand requestCarBrand) {
        return carBrandService.addCarBrand(requestCarBrand);
    }

    @GetMapping("/{id}")
    public ResponseCarBrand getCarBrand(@PathVariable Integer id) {
        return carBrandService.getCarBrand(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBrands() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carBrandService.getAllBrands());
    }

    @GetMapping("/maker/{id}")
    public ResponseEntity<Object> getAllVehicleByCarBrand(@PathVariable Integer id){
        List<ResponseGetAllVehicleByCarBrand> responseGetAllVehicleByCarBrandList = carBrandService.getAllVehicleByCarBrand(id);

        if (responseGetAllVehicleByCarBrandList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseGetAllVehicleByCarBrandList);
        }
    }
}
