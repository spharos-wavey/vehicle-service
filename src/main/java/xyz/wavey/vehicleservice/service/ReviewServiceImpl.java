package xyz.wavey.vehicleservice.service;

import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_REVIEW;
import static xyz.wavey.vehicleservice.base.exception.ErrorCode.NOT_FOUND_VEHICLE;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = false)
    public Review addReview(RequestReview requestReview) {
        return  reviewRepo.save(Review.builder()
            .vehicle(vehicleRepo.findById(requestReview.getVehicleId())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_VEHICLE.getMessage(),
                    NOT_FOUND_VEHICLE.getHttpStatus())))
            .content(requestReview.getContent())
            .type(requestReview.getType())
            .nickName(requestReview.getNickName())
            .profile(requestReview.getProfile())
            .build());
    }

    @Override
    @Transactional(readOnly = true)
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
