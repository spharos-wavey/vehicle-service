package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestMaker;
import xyz.wavey.vehicleservice.vo.ResponseGetAllMakers;

import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseMaker;

public interface MakerService {

    ResponseEntity<Object> addMaker(RequestMaker requestMaker);

    ResponseMaker getMaker(Integer id);
    List<ResponseGetAllMakers> getAllBrands();

    ResponseEntity<Object> getAllVehicleByMaker(Integer id);

}
