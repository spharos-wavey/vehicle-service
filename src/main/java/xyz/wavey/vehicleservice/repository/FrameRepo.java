package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Frame;

public interface FrameRepo extends JpaRepository<Frame, Long> {

}
