package xyz.wavey.vehicleservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.vo.*;

import java.util.List;

public interface CarBrandService {

    CarBrand addCarBrand(RequestCarBrand requestCarBrand);

    ResponseCarBrand getCarBrand(Integer id);

    List<ResponseGetAllCarBrands> getAllBrands();

    Slice<DtoFindAllByFrameId> getAllVehicleByCarBrand(Integer id, double lat, double lng, Pageable pageable);

}
