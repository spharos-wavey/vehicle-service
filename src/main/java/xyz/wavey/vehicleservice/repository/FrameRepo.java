package xyz.wavey.vehicleservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Frame;

public interface FrameRepo extends JpaRepository<Frame, Long> {

    List<Frame> findAllByCarBrandId(Integer carBrandId);

}
