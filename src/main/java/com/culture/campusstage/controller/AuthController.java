package com.culture.campusstage.controller;

import com.culture.campusstage.dto.LoginRequestDTO;
import com.culture.campusstage.dto.SignupRequestDTO;
import com.culture.campusstage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}