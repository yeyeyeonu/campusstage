package com.culture.campusstage.controller;

import com.culture.campusstage.dto.LoginRequestDto;
import com.culture.campusstage.dto.LoginResponseDto;
import com.culture.campusstage.dto.SignupRequestDto;
import com.culture.campusstage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    // 아이디 중복 확인
    @GetMapping("/check-userid")
    public Map<String, Boolean> checkUserid(@RequestParam String userid) {
        boolean available = userService.checkUseridAvailable(userid);
        return Map.of("available", available);
    }

    // 닉네임 중복 확인
    @GetMapping("/check-nickname")
    public Map<String, Boolean> checkNickname(@RequestParam String nickname) {
        boolean available = userService.checkNicknameAvailable(nickname);
        return Map.of("available", available);
    }
}