package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;

import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;

public interface CarBrandService {

    ResponseEntity<Object> addCarBrand(RequestCarBrand requestCarBrand);

    ResponseCarBrand getCarBrand(Integer id);
    List<ResponseGetAllCarBrands> getAllBrands();

    ResponseEntity<Object> getAllVehicleByCarBrand(Integer id);

}
