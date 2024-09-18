package com.office.seoul.facility.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping("/add")
    public ResponseEntity<String> addReview(
            @RequestParam("facilityId") String facilityId,
            @RequestParam("userId") String userId,
            @RequestParam("reviewText") String reviewText,
            @RequestParam("reviewRating") int reviewRating) {
        try {
            // 로그로 DTO 값을 확인
            log.info("Received review data: facilityId={}, userId={}, reviewText={}, reviewRating={}",
                    facilityId, userId, reviewText, reviewRating);

            // 리뷰 추가
            reviewService.addReview(facilityId, userId, reviewText, reviewRating);

            return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            log.error("후기 등록 실패: {}", e.getMessage());
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("후기 등록에 실패했습니다.");
        }
    }

    @GetMapping("/{facilityId}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("facilityId") String facilityId) {
        List<ReviewDto> reviews = reviewService.getReviews(facilityId);

        return ResponseEntity.ok(reviews != null ? reviews : new ArrayList<>());
    }
}
