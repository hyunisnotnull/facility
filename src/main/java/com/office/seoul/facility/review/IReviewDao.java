package com.office.seoul.facility.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IReviewDao {
	
	// 리뷰 추가
	int insertReview(ReviewDto reviewDto);

    // 특정 시설에 대한 리뷰 조회
    List<ReviewDto> selectReviewsByFacilityId(@Param("facilityId") String facilityId);

}
