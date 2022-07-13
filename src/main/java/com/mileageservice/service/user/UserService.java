package com.mileageservice.service.user;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
import com.mileageservice.repository.point.PointRepository;
import com.mileageservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
        return uuid;

    }


}
