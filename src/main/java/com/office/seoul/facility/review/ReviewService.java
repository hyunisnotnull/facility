package com.office.seoul.facility.review;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	
	private final IReviewDao iReviewDao;
	
	public ReviewService(IReviewDao iReviewDao) {
		this.iReviewDao = iReviewDao;
	}

    public List<ReviewDto> getReviews(String facilityId) {
        return iReviewDao.selectReviewsByFacilityId(facilityId);
    }

    public void addReview(String facilityId, String userId, String reviewText, int reviewRating) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setSVCID(facilityId);
        reviewDto.setU_m_id(userId);
        reviewDto.setC_text(reviewText);
        reviewDto.setC_rank(reviewRating);
        
        iReviewDao.insertReview(reviewDto);
    }

}
