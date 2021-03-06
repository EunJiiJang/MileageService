package com.mileageservice.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewReqDto {
    private String type;

    private String action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;


}
