package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_CAR_BRAND;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_MAKER;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.Frame;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.model.Vehicle;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.repository.CarBrandRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;

import java.util.ArrayList;
import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepo carBrandRepo;
    private final FrameRepo frameRepo;
    private final VehicleRepo vehicleRepo;

    @Override
    public ResponseEntity<Object> addCarBrand(RequestCarBrand requestCarBrand) {
        CarBrand carBrand = carBrandRepo.save(CarBrand.builder()
            .brandName(requestCarBrand.getBrandName())
            .foreignCar(requestCarBrand.getForeignCar())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(carBrand);
    }

    @Override
    public ResponseCarBrand getCarBrand(Integer id) {
        CarBrand carBrand = carBrandRepo.findById(id).orElseThrow(
            () -> new ServiceException(NOT_FOUND_MAKER.getMessage(),
                NOT_FOUND_MAKER.getHttpStatus()));
        return ResponseCarBrand.builder()
            .brandName(carBrand.getBrandName())
            .foreignCar(carBrand.getForeignCar())
            .build();
    }

    @Override
    public List<ResponseGetAllCarBrands> getAllBrands() {
        List<ResponseGetAllCarBrands> returnValue = new ArrayList<>();

        for (CarBrand carBrand : carBrandRepo.findAll()) {
            returnValue.add(ResponseGetAllCarBrands.builder()
                .id(carBrand.getId())
                .brandName(carBrand.getBrandName())
                .brandImgUrl(carBrand.getBrandImage())
                .build());
        }
        return returnValue;
    }

    @Override
    public List<ResponseGetAllVehicleByCarBrand> getAllVehicleByCarBrand(Integer id) {
        List<ResponseGetAllVehicleByCarBrand> returnValue = new ArrayList<>();

        CarBrand carBrand = carBrandRepo.findById(id).orElseThrow(() ->
                new ServiceException(NOT_FOUND_CAR_BRAND.getMessage(), NOT_FOUND_CAR_BRAND.getHttpStatus()));

        for(Frame frame : frameRepo.findAllByCarBrandId(id)) {
            for (Vehicle vehicle : vehicleRepo.findAllByFrameId(frame.getId())) {
                returnValue.add(ResponseGetAllVehicleByCarBrand.builder()
                        .carName(frame.getCarName())
                        .imageUrl(frame.getImage())
                        .charge(vehicle.getCharge())
                        .carBrandName(carBrand.getBrandName())
                        .zoneAddress(vehicle.getLastZone().getZoneAddress())
                        .billitaZone(vehicle.getLastZone().getName())
                        .build());
            }
        }
        return returnValue;

    }
}
