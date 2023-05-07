package xyz.wavey.vehicleservice.service;

import org.springframework.http.ResponseEntity;
import xyz.wavey.vehicleservice.vo.RequestReview;
import xyz.wavey.vehicleservice.vo.ResponseReview;

public interface ReviewService {

    ResponseEntity<Object> addReview(RequestReview requestReview);

    ResponseReview getReview(Long id);
}
