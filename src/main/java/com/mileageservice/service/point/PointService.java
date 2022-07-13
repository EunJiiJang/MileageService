package com.mileageservice.service.point;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
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
                User user = new User();
                user.user(userId);
                Optional<Point> point = pointRepository.findByUserId(user);
                return  new PointResDto(point.get().getUserId(),point.get().getTotalPoint());
            }

        public void saveDefaultPoint(UUID userId){
            User user = new User();
            user.user(userId);
            Point point = new Point();
            point.saveDefaultPoint(user,0);
            pointRepository.save(point);
        }
}
