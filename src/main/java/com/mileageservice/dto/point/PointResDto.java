package com.mileageservice.dto.point;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PointResDto {
    private UUID userId;

    private int totalPoint;

    public PointResDto(User user, int totalPoint) {
        this.userId = user.getUserId();
        this.totalPoint = totalPoint;
    }
}
