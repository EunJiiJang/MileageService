package com.mileageservice.dto.review;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ReviewReqDto {
    private String type;

    private String action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;
}
