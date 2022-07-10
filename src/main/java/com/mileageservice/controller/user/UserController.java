package com.mileageservice.controller.user;

import com.mileageservice.domain.user.User;
import com.mileageservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public void SaveUser(){
        userService.saveUser();
    }
}
