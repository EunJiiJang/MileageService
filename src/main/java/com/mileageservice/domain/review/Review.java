package com.mileageservice.domain.review;

import com.mileageservice.domain.user.User;
import com.mileageservice.dto.review.ReviewReqDto;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Table(name = "TRV01MT")
public class Review {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;

    @Column
    private String content;

    @Column
    private String attachedPhotoIds;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @Column
    private UUID placeId;

    @Column
    @CreationTimestamp
    private Timestamp regDate;

    @Column
    @UpdateTimestamp
    private Timestamp updDate;

    public void saveReview(String content,String attachedPhotoIds,User userId,UUID placeId){
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public void updataReview(UUID reviewId,String content,String attachedPhotoIds,User userId,UUID placeId,Timestamp regDate){
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
        this.regDate = regDate;
    }

}
