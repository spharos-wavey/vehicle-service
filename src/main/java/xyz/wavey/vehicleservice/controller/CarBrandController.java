package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.CarBrandService;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carbrand")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @PostMapping()
    public ResponseEntity<Object> addCarBrand(@RequestBody RequestCarBrand requestCarBrand) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(carBrandService.addCarBrand(requestCarBrand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarBrand(@PathVariable Integer id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(carBrandService.getCarBrand(id));
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBrands() {
        List<ResponseGetAllCarBrands> responseGetAllCarBrands = carBrandService.getAllBrands();

        if (responseGetAllCarBrands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseGetAllCarBrands);
        }
    }

    @GetMapping("/maker/{id}")
    public ResponseEntity<Object> getAllVehicleByCarBrand(@PathVariable Integer id,
        @RequestParam(required = false, value = "lat") String lat,
        @RequestParam(required = false, value = "lng") String lng,
        Pageable pageable) {

        if (lat == null || lng == null) {
            lat = "35.165826559288";
            lng = "129.132569894110";
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(carBrandService.getAllVehicleByCarBrand(id, Double.parseDouble(lat), Double.parseDouble(lng), pageable));
    }
}
