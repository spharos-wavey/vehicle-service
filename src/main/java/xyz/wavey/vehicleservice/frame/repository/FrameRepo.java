package xyz.wavey.vehicleservice.frame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.frame.model.Frame;

public interface FrameRepo extends JpaRepository<Frame, Long> {

}
