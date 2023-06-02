package xyz.wavey.vehicleservice.service;

import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;

import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByCarBrand;

public interface CarBrandService {

    CarBrand addCarBrand(RequestCarBrand requestCarBrand);

    ResponseCarBrand getCarBrand(Integer id);

    List<ResponseGetAllCarBrands> getAllBrands();

    List<ResponseGetAllVehicleByCarBrand> getAllVehicleByCarBrand(Integer id, double lat, double lng);
}
