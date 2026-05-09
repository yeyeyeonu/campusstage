package com.culture.campusstage.controller;

import com.culture.campusstage.dto.FavoriteResponseDto;
import com.culture.campusstage.dto.ReviewResponseDto;
import com.culture.campusstage.dto.WatchHistoryResponseDto;
import com.culture.campusstage.entity.User;
import com.culture.campusstage.repository.UserRepository;
import com.culture.campusstage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserRepository userRepository;
    private final MyPageService myPageService;

    @GetMapping("/{userId}")
    public User getMyPage(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @GetMapping("/{userId}/favorites")
    public List<FavoriteResponseDto> getFavorites(@PathVariable Long userId) {
        return myPageService.getFavorites(userId);
    }

    @GetMapping("/{userId}/reviews")
    public List<ReviewResponseDto> getReviews(@PathVariable Long userId) {
        return myPageService.getReviews(userId);
    }

    @GetMapping("/{userId}/watch-history")
    public List<WatchHistoryResponseDto> getWatchHistories(@PathVariable Long userId) {
        return myPageService.getWatchHistories(userId);
    }
}