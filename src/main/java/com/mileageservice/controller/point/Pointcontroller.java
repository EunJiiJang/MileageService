package com.mileageservice.controller.point;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
import com.mileageservice.dto.common.res.ResDto;
import com.mileageservice.dto.point.PointResDto;
import com.mileageservice.service.point.PointService;
import com.mileageservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class Pointcontroller {
    private final PointService pointService;
    private final UserService userService;

    @PostMapping("/getPoint")
    public ResponseEntity<Object> getPointbyUser(@RequestParam(value = "userId") UUID userId){
        PointResDto pointResDto = pointService.getPointByUser(userId);
        return new ResponseEntity<>(
                new ResDto("성공적으로 조회되었습니다.", pointResDto),
                HttpStatus.OK
        );
    }

    @PostMapping("/Test")
    public ResponseEntity<Object> Test(){
        UUID userId = userService.saveUser();
        return new ResponseEntity<>(
                new ResDto("성공적으로 조회되었습니다.", ""),
                HttpStatus.OK
        );
    }
}
