package com.mileageservice.service.user;

import com.mileageservice.domain.user.User;
import com.mileageservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(){
        User user = new User();
        userRepository.save(user);
    }
}
