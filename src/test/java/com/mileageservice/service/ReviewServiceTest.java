package com.mileageservice.service;

import com.mileageservice.domain.review.Review;
import com.mileageservice.dto.point.PointResDto;
import com.mileageservice.dto.review.ReviewReqDto;
import com.mileageservice.service.point.PointService;
import com.mileageservice.service.review.ReviewService;
import com.mileageservice.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    PointService pointService;


    UUID createdUserId = UUID.fromString("8c787799-747d-4284-93a0-b476a2ad20a0");
    UUID reviewId = UUID.randomUUID();
    String type = "REVIEW";
    String action;
    String content = "좋아요";
    List<UUID> attachedPhotoIds = new ArrayList<>();
    UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    String attachedPhotoIdsStr = "e4d1a64e-a531-46de-88d0-ff0ed70c0bb8,afb0cef2-851d-4a50-bb07-9cc15cbdc332";

    Review review;


    @AfterEach
    void 유저포인트_조회하기(){
        //given
        //when
        PointResDto pointResDto = pointService.getPointByUser(createdUserId);
        pointResDto.getTotalPoint();
        //then
    }


    @Test
    @Order(1)
    void 리뷰_작성하기(){
        //given
        action = "ADD";
        attachedPhotoIds.add(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"));
        attachedPhotoIds.add(UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc332"));

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
    @Order(2)
    void 리뷰_수정하기(){
        //given
        action = "ADD";
        attachedPhotoIds.add(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"));
        attachedPhotoIds.add(UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc332"));
        ReviewReqDto firstReviewReqDto = new ReviewReqDto(type,action,reviewId,content,attachedPhotoIds,createdUserId,placeId);
        this.review = reviewService.saveReview(firstReviewReqDto);

        action = "MOD";
        content = "수정한 내용입니다.";
        attachedPhotoIds =new ArrayList<>();
        //when
        ReviewReqDto reviewReqDto = new ReviewReqDto(type,action,reviewId,content,attachedPhotoIds,createdUserId,placeId);
        this.review = reviewService.modifyReview(reviewReqDto);

        //then
        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(review.getAttachedPhotoIds()).isEqualTo("");
        Assertions.assertThat(review.getUserId().getUserId()).isEqualTo(createdUserId);
        Assertions.assertThat(review.getPlaceId()).isEqualTo(placeId);
    }

    @Test
    @Order(3)
    void 리뷰_삭제하기(){
        //given
        action = "ADD";
        attachedPhotoIds.add(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"));
        attachedPhotoIds.add(UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc332"));
        ReviewReqDto firstReviewReqDto = new ReviewReqDto(type,action,reviewId,content,attachedPhotoIds,createdUserId,placeId);
        this.review = reviewService.saveReview(firstReviewReqDto);

        action = "DELETE";

        //when
        ReviewReqDto reviewReqDto = new ReviewReqDto(type,action,reviewId,content,attachedPhotoIds,createdUserId,placeId);
        reviewService.deleteReview(reviewReqDto);

        //then
        //해당장소에 해당유저의 리뷰갯수가 없어야함.
        int reviewCount = reviewService.getPlaceUserReviewByUserId(reviewReqDto.getUserId(),reviewReqDto.getPlaceId());
        Assertions.assertThat(reviewCount).isEqualTo(0);
    }


}
