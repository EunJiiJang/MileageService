package com.mileageservice.service.point;

import com.mileageservice.domain.point.Point;
import com.mileageservice.dto.point.PointResDto;
import com.mileageservice.repository.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PointService {
        private final PointRepository pointRepository;

        public PointResDto getPointByUser(UUID userId){
            Optional<Point> point = pointRepository.findById(userId);
            return  new PointResDto(point.get().getUser(),point.get().getTotalPoint());
        }
}
