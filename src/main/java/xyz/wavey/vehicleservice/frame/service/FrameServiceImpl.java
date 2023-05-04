package xyz.wavey.vehicleservice.frame.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.frame.model.Frame;
import xyz.wavey.vehicleservice.frame.repository.FrameRepo;
import xyz.wavey.vehicleservice.frame.vo.RequestFrame;
import xyz.wavey.vehicleservice.frame.vo.ResponseFrame;

@Service
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService{

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
         .build());
     return ResponseEntity.status(HttpStatus.OK).body(frame);
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
        .build();
  }
}