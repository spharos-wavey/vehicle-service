package xyz.wavey.vehicleservice.service;

import xyz.wavey.vehicleservice.model.Frame;
import xyz.wavey.vehicleservice.vo.RequestFrame;
import xyz.wavey.vehicleservice.vo.ResponseFrame;

public interface FrameService {

    Frame addFrame(RequestFrame requestFrame);

    ResponseFrame getFrame(Long id);
}
