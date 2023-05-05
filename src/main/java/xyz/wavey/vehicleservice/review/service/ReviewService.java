package xyz.wavey.vehicleservice.review.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.review.vo.RequestReview;
import xyz.wavey.vehicleservice.review.vo.ResponseReview;

public interface ReviewService {

  ResponseEntity<Object> addReview(RequestReview requestReview);

  ResponseReview getReview(Long id);
}
