package com.mileageservice.service;

import com.mileageservice.domain.review.Review;
import com.mileageservice.dto.review.ReviewReqDto;
import com.mileageservice.service.point.PointService;
import com.mileageservice.service.review.ReviewService;
import com.mileageservice.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    PointService pointService;

    UUID createdUserId = UUID.randomUUID();
    UUID reviewId;
    String type = "REVIEW";
    String action;
    String content = "좋아요";
    List<UUID> attachedPhotoIds;
    UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    String attachedPhotoIdsStr = "e4d1a64e-a531-46de-88d0-ff0ed70c0bb8,afb0cef2-851d-4a50-bb07-9cc15cbdc332";

    Review review;


    @AfterEach
    public void cleanUp(){

    }
    @BeforeEach
    void init(){
        userService.saveUser(createdUserId);
    }


    @Test
    void 리뷰_작성하기(){
        //given
        action = "ADD";
        //when
        ReviewReqDto reviewReqDto = new ReviewReqDto(type,action,reviewId,content,attachedPhotoIds,createdUserId,placeId);
        this.review = reviewService.saveReview(reviewReqDto);

        //then
        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(review.getAttachedPhotoIds()).isEqualTo(attachedPhotoIdsStr);
        Assertions.assertThat(review.getUserId().getUserId()).isEqualTo(createdUserId);
        Assertions.assertThat(review.getPlaceId()).isEqualTo(placeId);
    }

    @Test
    void 리뷰_수정하기(){
        //given

        //when

        //then
    }

    @Test
    void 리뷰_삭제하기(){
        //given

        //when

        //then
    }

}
