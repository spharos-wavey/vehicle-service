package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_REVIEW;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_Review;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_VEHICLE;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.base.exception.ServiceException;
import xyz.wavey.vehicleservice.model.Review;
import xyz.wavey.vehicleservice.repository.ReviewRepo;
import xyz.wavey.vehicleservice.repository.VehicleRepo;
import xyz.wavey.vehicleservice.vo.RequestReview;
import xyz.wavey.vehicleservice.vo.ResponseReview;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final VehicleRepo vehicleRepo;

    @Override
    public ResponseEntity<Object> addReview(RequestReview requestReview) {
        Review review = reviewRepo.save(Review.builder()
            .vehicle(vehicleRepo.findById(requestReview.getVehicleId())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
                    NOT_FOUND_VEHICLE.getHttpStatus())))
            .content(requestReview.getContent())
            .type(requestReview.getType())
            .nickName(requestReview.getNickName())
            .profile(requestReview.getProfile())
            .build());
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @Override
    public ResponseReview getReview(Long id) {
        Review review = reviewRepo.findById(id)
            .orElseThrow(() -> new ServiceException(NOT_FOUND_REVIEW.getMessage(),
                NOT_FOUND_REVIEW.getHttpStatus()));
        return ResponseReview.builder()
            .content(review.getContent())
            .type(review.getType())
            .nickName(review.getNickName())
            .profile(review.getProfile())
            .build();
    }
}
