package xyz.wavey.vehicleservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Review;
import xyz.wavey.vehicleservice.vo.ReviewInfoMapping;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<ReviewInfoMapping> findAllByVehicleId(Long vehicleId);
}
