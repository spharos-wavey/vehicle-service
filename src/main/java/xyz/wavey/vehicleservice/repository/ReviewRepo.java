package xyz.wavey.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
