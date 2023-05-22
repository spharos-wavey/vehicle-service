package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_MAKER;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.Frame;
import xyz.wavey.vehicleservice.model.Maker;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.repository.MakerRepo;
import xyz.wavey.vehicleservice.vo.RequestMaker;
import xyz.wavey.vehicleservice.vo.ResponseGetAllMakers;

import java.util.ArrayList;
import java.util.List;
import xyz.wavey.vehicleservice.vo.ResponseGetAllVehicleByMaker;
import xyz.wavey.vehicleservice.vo.ResponseMaker;

@Service
@RequiredArgsConstructor
public class MakerServiceImpl implements MakerService {

    private final MakerRepo makerRepo;
    private final FrameRepo frameRepo;

    @Override
    public ResponseEntity<Object> addMaker(RequestMaker requestMaker) {
        Maker maker = makerRepo.save(Maker.builder()
            .name(requestMaker.getName())
            .foreignCar(requestMaker.getForeignCar())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(maker);
    }

    @Override
    public ResponseMaker getMaker(Integer id) {
        Maker maker = makerRepo.findById(id).orElseThrow(
            () -> new ServiceException(NOT_FOUND_MAKER.getMessage(),
                NOT_FOUND_MAKER.getHttpStatus()));
        return ResponseMaker.builder()
            .name(maker.getName())
            .foreignCar(maker.getForeignCar())
            .build();
    }

    @Override
    public List<ResponseGetAllMakers> getAllBrands() {
        List<ResponseGetAllMakers> returnValue = new ArrayList<>();

        for (Maker maker : makerRepo.findAll()) {
            returnValue.add(ResponseGetAllMakers.builder()
                .id(maker.getId())
                .name(maker.getName())
                .build());
        }
        return returnValue;
    }

    @Override
    public ResponseEntity<Object> getAllVehicleByMaker(Integer id) {
        List<ResponseGetAllVehicleByMaker> responseValue = new ArrayList<>();
        List<Frame> frameList = frameRepo.findAllByMakerId(id);

        for(Frame frame : frameList) {
            responseValue.add(ResponseGetAllVehicleByMaker.builder()
                .carModel(frame.getName())
                .imageUrl(frame.getImage())
                .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseValue);

    }
}
