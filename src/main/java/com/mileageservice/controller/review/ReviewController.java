package com.mileageservice.controller.review;

import com.mileageservice.dto.common.res.ResDto;
import com.mileageservice.dto.review.ReviewReqDto;
import com.mileageservice.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;
    /**
     *
     * <PRE>
     * 설명 : 리뷰 작성/수정/삭제
     * <PRE>
     * @author JangEunJi
     * @since 2022. 07. 09
     */
    @PostMapping("/events")
    public ResponseEntity<Object> reviewEvents(@RequestBody ReviewReqDto reviewReqDto)throws Exception{
       String msg = "성공적으로 실행되었습니다.";
        if(("ADD").equals(reviewReqDto.getAction())){
            //리뷰작성이력이 있을때
            if (reviewService.getPlaceUserReviewByUserId(reviewReqDto.getUserId(),reviewReqDto.getPlaceId()) > 0){
                return new ResponseEntity<>(
                        new ResDto("이미 리뷰를 작성한 장소입니다.", ""),
                        HttpStatus.CONFLICT
                );
            }else {
                reviewService.saveReview(reviewReqDto);
            }
       }else if(("MOD").equals(reviewReqDto.getAction())){
            reviewService.modifyReview(reviewReqDto);
       }else{
            reviewService.deleteReview(reviewReqDto);
       }
        return new ResponseEntity<>(
                new ResDto("성공적으로 실행되었습니다.", ""),
                HttpStatus.OK
        );
    }
}
