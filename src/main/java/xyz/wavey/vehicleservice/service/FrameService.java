package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestFrame;
import xyz.wavey.vehicleservice.vo.ResponseFrame;

public interface FrameService {

    ResponseEntity<Object> addFrame(RequestFrame requestFrame);

    ResponseFrame getFrame(Long id);
}
