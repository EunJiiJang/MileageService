package com.mileageservice.service.user;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
import com.mileageservice.repository.point.PointRepository;
import com.mileageservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    public UUID saveUser(UUID uuid){

        User user = new User();
        user.user(uuid);
        userRepository.save(user);

        //회원생성
        Point point = new Point();
        point.saveDefaultPoint(user,0);
        pointRepository.save(point);

        return uuid;

    }

}
