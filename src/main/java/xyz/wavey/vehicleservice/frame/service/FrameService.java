package xyz.wavey.vehicleservice.frame.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.wavey.vehicleservice.frame.vo.RequestFrame;
import xyz.wavey.vehicleservice.frame.vo.ResponseFrame;

public interface FrameService {

  ResponseEntity<Object> addFrame(RequestFrame requestFrame);

  ResponseFrame getFrame(Long id);
}
