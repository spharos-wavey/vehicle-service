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
        List<ResponseGetAllVehicleByCarBrand> returnValue = new ArrayList<>();
        returnValue.add(ResponseGetAllVehicleByCarBrand.builder()
                        .carName("테스트 차량 이름1")
                        .imageUrl("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                        .charge(90)
                        .carBrandName("테스트 브랜드명1")
                        .zoneAddress("테스트 도로명 주소1")
                        .billitaZone("테스트 빌리타존 이름1")
                .build());

        returnValue.add(ResponseGetAllVehicleByCarBrand.builder()
                .carName("테스트 차량 이름2")
                .imageUrl("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                .charge(90)
                .carBrandName("테스트 브랜드명2")
                .zoneAddress("테스트 도로명 주소2")
                .billitaZone("테스트 빌리타존 이름2")
                .build());

        returnValue.add(ResponseGetAllVehicleByCarBrand.builder()
                .carName("테스트 차량 이름3")
                .imageUrl("https://storage.googleapis.com/bucket_billita_vehicle/ac6050b590c42d7f/Audi/%EC%95%84%EC%9A%B0%EB%94%94_%EC%9D%B4%ED%8A%B8%EB%A1%A0%20GT_%EC%8A%A4%EC%A6%88%EC%B9%B4%20%EA%B7%B8%EB%A0%88%EC%9D%B4%20%EB%A9%94%ED%83%88%EB%A6%AD.png")
                .charge(90)
                .carBrandName("테스트 브랜드명3")
                .zoneAddress("테스트 도로명 주소3")
                .billitaZone("테스트 빌리타존 이름3")
                .build());
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
