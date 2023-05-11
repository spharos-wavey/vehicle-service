package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.Frame;
import xyz.wavey.vehicleservice.repository.FrameRepo;
import xyz.wavey.vehicleservice.vo.RequestFrame;
import xyz.wavey.vehicleservice.vo.ResponseFrame;

@Service
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService {

    private final FrameRepo frameRepo;

    @Override
    public ResponseEntity<Object> addFrame(RequestFrame requestFrame) {
        Frame frame = frameRepo.save(Frame.builder()
            .maker(requestFrame.getMaker())
            .foreignCar(requestFrame.getForeignCar())
            .name(requestFrame.getName())
            .capacity(requestFrame.getCapacity())
            .recommend(requestFrame.getRecommend())
            .defaultPrice(requestFrame.getDefaultPrice())
            .distancePrice(requestFrame.getDistancePrice())
            .carType(requestFrame.getCarType())
            .appearance(requestFrame.getAppearance())
            .manuel(requestFrame.getManuel())
            .color(requestFrame.getColor())
            .image(requestFrame.getImage())
            .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(frame);
    }

    @Override
    public ResponseFrame getFrame(Long id) {
        Frame frame = frameRepo.findById(id).orElseThrow(() -> new ServiceException("error"));
        return ResponseFrame.builder()
            .maker(frame.getMaker())
            .recommend(frame.getRecommend())
            .capacity(frame.getCapacity())
            .foreignCar(frame.getForeignCar())
            .name(frame.getName())
            .defaultPrice(frame.getDefaultPrice())
            .distancePrice(frame.getDistancePrice())
            .carType(frame.getCarType())
            .appearance(frame.getAppearance())
            .manuel(frame.getManuel())
            .color(frame.getColor())
            .image(frame.getImage())
            .build();
    }
}
