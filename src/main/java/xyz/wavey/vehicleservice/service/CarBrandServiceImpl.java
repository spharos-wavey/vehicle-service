package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.CarBrand;
import xyz.wavey.vehicleservice.repository.CarBrandRepo;
import xyz.wavey.vehicleservice.vo.*;

import java.util.ArrayList;
import java.util.List;
import xyz.wavey.vehicleservice.vo.RequestCarBrand;
import xyz.wavey.vehicleservice.vo.ResponseGetAllCarBrands;
import xyz.wavey.vehicleservice.vo.ResponseCarBrand;

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
    public Slice<DtoFindAllByFrameId> getAllVehicleByCarBrand(Integer id, double lat, double lng, Pageable pageable) {
        return carBrandRepo.findAllByFrameId(id, lat, lng, pageable);
    }
}
