package com.mileageservice.repository.review;

import com.mileageservice.domain.review.Review;
import com.mileageservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    int countByPlaceId(UUID placeId);

    int countByUserIdAndPlaceId(User userId, UUID placeId);

    Review findByUserIdAndPlaceId(User userId, UUID placeId);

    Review findTopByPlaceIdOrderByRegDateAsc(UUID placeId);   //해당장소의 첫번째 리뷰

    @Transactional
    void deleteByReviewIdAndPlaceId(UUID reviewId,UUID placeId);

}
