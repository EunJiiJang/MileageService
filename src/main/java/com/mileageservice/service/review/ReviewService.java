package com.mileageservice.service.review;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.pointHistory.PointHistory;
import com.mileageservice.domain.review.Review;
import com.mileageservice.domain.user.User;
import com.mileageservice.dto.review.ReviewReqDto;
import com.mileageservice.repository.point.PointRepository;
import com.mileageservice.repository.pointHistory.PointHistoryRepository;
import com.mileageservice.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public Review saveReview(ReviewReqDto reviewReqDto) {

        int point = 0;      //리뷰에 따른 증가포인트
        List<UUID> attachIds = reviewReqDto.getAttachedPhotoIds();      //리뷰 첨부 이미지Ids
        int attachIdsSize = attachIds.size();       //리뷰 첨부 이미지 갯수
        int reviewCount = reviewRepository.countByPlaceId(reviewReqDto.getPlaceId());       //리뷰를 작성하는 장소의 기존리뷰갯수

        if (reviewReqDto.getContent().length() > 0) {
            point ++;
        }
        if (attachIdsSize > 0) {
            point ++;
        }
        if (reviewCount == 0) {
            point ++;
        }


        String attachIdsStr ="";
        for (int i = 0; i < attachIdsSize; i++) {
            attachIdsStr += attachIds.get(i).toString();
            if(i < (attachIdsSize -1) ){
                attachIdsStr += ",";
            }
        }
        User userData = new User();
        userData.user(reviewReqDto.getUserId());

        //포인트 이벤트 발생이력
        PointHistory pointHistoryData = new PointHistory();
        pointHistoryData.savePointHistory(userData,reviewReqDto.getType(),reviewReqDto.getReviewId(),reviewReqDto.getAction(),point);
        log.debug("Save point issue history");
        pointHistoryRepository.save(pointHistoryData);


        //회원의 총포인트 저장/업데이트
        Optional<Point> userPoint = pointRepository.findByUserId(userData);     //회원의 기존 총포인트 데이터
        int orginTotalPoint = userPoint.get().getTotalPoint();      //회원의 기존 총포인트
        Point pointData = new Point();
        pointData.updatePoint(userPoint.get().getTotalPointid(),userData,orginTotalPoint+point,userPoint.get().getRegDate());
        log.debug("Update Totalpoint by userId = "+reviewReqDto.getUserId()+",totalPoint = "+orginTotalPoint+point);
        pointRepository.save(pointData);

        //리뷰저장
        Review reviewData = new Review();
        reviewData.saveReview(reviewReqDto.getContent(),attachIdsStr,userData,reviewReqDto.getPlaceId());
        log.debug("Save Review about Place");
        return reviewRepository.save(reviewData);
    }
    @Transactional
    public Review modifyReview(ReviewReqDto reviewReqDto){

        //회원의 기존 리뷰데이터
        User userData = new User();
        userData.user(reviewReqDto.getUserId());
        Review reviewData = reviewRepository.findByUserIdAndPlaceId(userData,reviewReqDto.getPlaceId());


        int attachIdsSize = reviewReqDto.getAttachedPhotoIds().size();      //회원의 수정 리뷰이미지Ids
        //List<String> orginAttArr = List.of(reviewData.getAttachedPhotoIds().split(","));
        int originAttachIdsSize = 0;    //회원의 기존 리뷰이미지Ids
        if(!reviewData.getAttachedPhotoIds().isEmpty()){
            originAttachIdsSize = reviewData.getAttachedPhotoIds().split(",").length;
        }

        int point = 0;      //리뷰에 따른 증가포인트
        //기존 리뷰-글,이미지 모두존재 ->이미지 삭제
        if(reviewData.getContent().length() > 0 && originAttachIdsSize > 0 && attachIdsSize == 0){
            point --;
        }
        //기존 리뷰-글만 존재 ->이미지 추가
        if(reviewData.getContent().length() > 0 && originAttachIdsSize == 0 && attachIdsSize > 0){
            point ++;
        }


        List<UUID> attachIds = reviewReqDto.getAttachedPhotoIds();      //수정하려는 리뷰 첨부 이미지Ids
        String attachIdsStr ="";
        for (int i = 0; i < attachIdsSize; i++) {
            attachIdsStr += attachIds.get(i).toString();
            if(i < (attachIdsSize -1) ){
                attachIdsStr += ",";
            }
        }

        //포인트 이벤트 발생이력
        PointHistory pointHistoryData = new PointHistory();
        pointHistoryData.savePointHistory(userData,reviewReqDto.getType(),reviewReqDto.getReviewId(),reviewReqDto.getAction(),point);
        log.debug("Save point issue history By modify");
        pointHistoryRepository.save(pointHistoryData);

        //회원의 총포인트 저장/업데이트
        Optional<Point> userPoint = pointRepository.findByUserId(userData);     //회원의 기존 총포인트 데이터
        int orginTotalPoint = userPoint.get().getTotalPoint();      //회원의 기존 총포인트
        Point pointData = new Point();
        pointData.updatePoint(userPoint.get().getTotalPointid(),userData,orginTotalPoint+point,userPoint.get().getRegDate());
        log.debug("Update Totalpoint by userId = "+reviewReqDto.getUserId()+",totalPoint = "+orginTotalPoint+point);
        pointRepository.save(pointData);

        //리뷰저장
        Review reviewUpdateData = new Review();
        reviewUpdateData.updataReview(reviewData.getReviewId(),reviewReqDto.getContent(),attachIdsStr,userData,reviewReqDto.getPlaceId(),reviewData.getRegDate());
        log.debug("Update Review about Place");
        return reviewRepository.save(reviewUpdateData);
    }
    @Transactional
    public void deleteReview(ReviewReqDto reviewReqDto){

        //회원의 기존 리뷰데이터
        User userData = new User();
        userData.user(reviewReqDto.getUserId());
        Review reviewData = reviewRepository.findByUserIdAndPlaceId(userData,reviewReqDto.getPlaceId());

        int attachIdsSize = 0;    //회원의 기존 리뷰이미지Ids
        if(!reviewData.getAttachedPhotoIds().isEmpty()){
            attachIdsSize = reviewData.getAttachedPhotoIds().split(",").length;
        }
        int point = 0;      //리뷰에 따른 증가포인트
        Review firstReview = reviewRepository.findTopByPlaceIdOrderByRegDateAsc(reviewReqDto.getPlaceId()); //삭제하려는 리뷰 장소의 첫번째 리뷰
        //삭제하려는 리뷰가 첫번째등록 리뷰일때
        if(reviewReqDto.getUserId().equals(firstReview.getUserId().getUserId())){
            point ++;
        }
        if (reviewReqDto.getContent().length() > 0) {
            point ++;
        }
        if (attachIdsSize > 0) {
            point ++;
        }
        //포인트 감소를 위해 음수로 변환
        point = point * -1;

        //포인트 이벤트 발생이력
        PointHistory pointHistoryData = new PointHistory();
        pointHistoryData.savePointHistory(userData,reviewReqDto.getType(),reviewReqDto.getReviewId(),reviewReqDto.getAction(),point);
        log.debug("Save point issue history By Delete");
        pointHistoryRepository.save(pointHistoryData);

        //회원의 총포인트 저장/업데이트
        Optional<Point> userPoint = pointRepository.findByUserId(userData);     //회원의 기존 총포인트 데이터
        int orginTotalPoint = userPoint.get().getTotalPoint();      //회원의 기존 총포인트
        Point pointData = new Point();
        pointData.updatePoint(userPoint.get().getTotalPointid(),userData,orginTotalPoint+point,userPoint.get().getRegDate());
        log.debug("Update Totalpoint by userId = "+reviewReqDto.getUserId()+",totalPoint = "+orginTotalPoint+point);
        pointRepository.save(pointData);

        //리뷰삭제
        log.debug("Delete Review about Place");
        reviewRepository.deleteByReviewIdAndPlaceId(reviewData.getReviewId(),reviewReqDto.getPlaceId());
    }

    public int getPlaceUserReviewByUserId(UUID userId,UUID placeId){
        User userData = new User();
        userData.user(userId);
        return reviewRepository.countByUserIdAndPlaceId(userData,placeId);
    }
}
