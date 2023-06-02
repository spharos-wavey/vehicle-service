package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_MAKER;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.repository.CarBrandRepo;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;

import java.util.ArrayList;
import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByCarBrandList;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepo carBrandRepo;

    @Override
    public CarBrand addCarBrand(RequestCarBrand requestCarBrand) {
        return carBrandRepo.save(CarBrand.builder()
            .brandName(requestCarBrand.getBrandName())
            .foreignCar(requestCarBrand.getForeignCar())
            .build());
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
    public List<ResponseGetAllVehicleByCarBrand> getAllVehicleByCarBrand(Integer id, double lat, double lng) {
        List<ResponseGetAllVehicleByCarBrand> returnValue = new ArrayList<>();

        List<ResponseGetAllVehicleByCarBrandList> carBrandLists = carBrandRepo.getAllVehicleByCarBrandList(id, lat,
            lng);
        for (ResponseGetAllVehicleByCarBrandList responseGetAllVehicleByCarBrandList : carBrandLists) {
            returnValue.add(ResponseGetAllVehicleByCarBrand.builder()
                .vehicleId(responseGetAllVehicleByCarBrandList.getVehicleId())
                .carName(responseGetAllVehicleByCarBrandList.getCarName())
                .imageUrl(responseGetAllVehicleByCarBrandList.getImageUrl())
                .charge(responseGetAllVehicleByCarBrandList.getCharge())
                .defaultPrice(responseGetAllVehicleByCarBrandList.getDefaultPrice())
                .distancePrice(responseGetAllVehicleByCarBrandList.getDistancePrice())
                .carBrandName(responseGetAllVehicleByCarBrandList.getCarBrandName())
                .zoneAddress(responseGetAllVehicleByCarBrandList.getZoneAddress())
                .billitaZone(responseGetAllVehicleByCarBrandList.getBillitaZone())
                .build());
        }
        return returnValue;
    }
}
