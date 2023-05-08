package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.Review;
import xyz.wavey.vehicleservice.repository.ReviewRepo;
import xyz.wavey.vehicleservice.vo.RequestReview;
import xyz.wavey.vehicleservice.vo.ResponseReview;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;

    @Override
    public ResponseEntity<Object> addReview(RequestReview requestReview) {
        Review review = reviewRepo.save(Review.builder()
            .content(requestReview.getContent())
            .type(requestReview.getType())
            .nickName(requestReview.getNickName())
            .profile(requestReview.getProfile())
            .build());
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @Override
    public ResponseReview getReview(Long id) {
        Review review = reviewRepo.findById(id).orElseThrow(() -> new ServiceException("error"));
        return ResponseReview.builder()
            .content(review.getContent())
            .type(review.getType())
            .nickName(review.getNickName())
            .profile(review.getProfile())
            .build();
    }
}
