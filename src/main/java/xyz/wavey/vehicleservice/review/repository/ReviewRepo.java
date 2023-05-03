package xyz.wavey.vehicleservice.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wavey.vehicleservice.review.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
