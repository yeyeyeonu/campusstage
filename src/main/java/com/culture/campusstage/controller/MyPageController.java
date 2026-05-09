package com.culture.campusstage.controller;

import com.culture.campusstage.entity.User;
import com.culture.campusstage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public User getMyPage(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}