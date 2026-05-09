package com.culture.campusstage.controller;

import com.culture.campusstage.dto.LoginRequestDTO;
import com.culture.campusstage.dto.SignupRequestDTO;
import com.culture.campusstage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 임시
    @GetMapping("/test")
    public String test() {
        return "서버 연결 성공";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDTO request) {

        userService.signup(request);

        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {

        boolean success = userService.login(request);

        if (success) {
            return "로그인 성공";
        }

        return "아이디 또는 비밀번호가 틀렸습니다.";

    }

    @GetMapping("/check-userid")
    public Map<String, Boolean> checkUserid(@RequestParam String userid) {
        boolean available = userService.checkUseridAvailable(userid);
        return Map.of("available", available);
    }

    @GetMapping("/check-nickname")
    public Map<String, Boolean> checkNickname(@RequestParam String nickname) {

        boolean available = userService.checkNicknameAvailable(nickname);
        return Map.of("available", available);
    }

}