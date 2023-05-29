package xyz.wavey.vehicleservice.service;

import xyz.wavey.vehicleservice.model.Review;
import xyz.wavey.vehicleservice.vo.RequestReview;
import xyz.wavey.vehicleservice.vo.ResponseReview;

public interface ReviewService {

    Review addReview(RequestReview requestReview);

    ResponseReview getReview(Long id);
}
