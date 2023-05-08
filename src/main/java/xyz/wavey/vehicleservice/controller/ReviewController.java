package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.ReviewService;
import xyz.wavey.vehicleservice.vo.RequestReview;
import xyz.wavey.vehicleservice.vo.ResponseReview;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Object> addReview(@RequestBody RequestReview requestReview) {
        return reviewService.addReview(requestReview);
    }

    @GetMapping("/{id}")
    public ResponseReview getReview(@PathVariable Long id) {
        return reviewService.getReview(id);
    }
}
