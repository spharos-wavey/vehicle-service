package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_FRAME;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_MAKER;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.Frame;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.repository.CarBrandRepo;
import xyz.wavey.vehicleservice.vo.RequestFrame;
import xyz.wavey.vehicleservice.vo.ResponseFrame;

@Service
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService {

    private final FrameRepo frameRepo;
    private final CarBrandRepo carBrandRepo;

    @Override
    public Frame addFrame(RequestFrame requestFrame) {
        return frameRepo.save(Frame.builder()
            .carBrand(carBrandRepo.findById(requestFrame.getCarBrandId()).orElseThrow(() ->
                new ServiceException(NOT_FOUND_MAKER.getMessage(),
                    NOT_FOUND_MAKER.getHttpStatus())))
            .carName(requestFrame.getCarName())
            .capacity(requestFrame.getCapacity())
            .recommend(requestFrame.getRecommend())
            .defaultPrice(requestFrame.getDefaultPrice())
            .distancePrice(requestFrame.getDistancePrice())
            .carType(requestFrame.getCarType())
            .appearance(requestFrame.getAppearance())
            .manual(requestFrame.getManual())
            .color(requestFrame.getColor())
            .image(requestFrame.getImage())
            .build());

    }

    @Override
    public ResponseFrame getFrame(Long id) {
        Frame frame = frameRepo.findById(id).orElseThrow(()
            -> new ServiceException(NOT_FOUND_FRAME.getMessage(),
            NOT_FOUND_FRAME.getHttpStatus()));
        return ResponseFrame.builder()
            .carBrand(frame.getCarBrand().getBrandName())
            .recommend(frame.getRecommend())
            .capacity(frame.getCapacity())
            .foreignCar(frame.getCarBrand().getForeignCar())
            .name(frame.getCarName())
            .defaultPrice(frame.getDefaultPrice())
            .distancePrice(frame.getDistancePrice())
            .carType(frame.getCarType())
            .appearance(frame.getAppearance())
            .manual(frame.getManual())
            .color(frame.getColor())
            .image(frame.getImage())
            .build();
    }
}
