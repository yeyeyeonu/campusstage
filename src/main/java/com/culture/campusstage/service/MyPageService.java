package com.culture.campusstage.service;

import com.culture.campusstage.dto.FavoriteResponseDto;
import com.culture.campusstage.dto.ReviewResponseDto;
import com.culture.campusstage.dto.WatchHistoryResponseDto;
import com.culture.campusstage.entity.Favorite;
import com.culture.campusstage.entity.Review;
import com.culture.campusstage.entity.User;
import com.culture.campusstage.entity.WatchHistory;
import com.culture.campusstage.repository.FavoriteRepository;
import com.culture.campusstage.repository.ReviewRepository;
import com.culture.campusstage.repository.UserRepository;
import com.culture.campusstage.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final ReviewRepository reviewRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    public List<FavoriteResponseDto> getFavorites(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<Favorite> favorites = favoriteRepository.findByUser(user);

        return favorites.stream()
                .map(favorite -> new FavoriteResponseDto(
                        favorite.getPerformance().getId(),
                        favorite.getPerformance().getName(),
                        favorite.getPerformance().getPosterUrl()
                ))
                .toList();
    }

    public List<ReviewResponseDto> getReviews(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<Review> reviews = reviewRepository.findByUser(user);

        return reviews.stream()
                .map(review -> new ReviewResponseDto(
                        review.getId(),
                        review.getPerformance().getId(),
                        review.getPerformance().getName(),
                        review.getContent(),
                        review.getCreatedAt()
                ))
                .toList();
    }

    public List<WatchHistoryResponseDto> getWatchHistories(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<WatchHistory> histories = watchHistoryRepository.findByUser(user);

        return histories.stream()
                .map(history -> new WatchHistoryResponseDto(
                        history.getPerformance().getId(),
                        history.getPerformance().getName(),
                        history.getPerformance().getPosterUrl(),
                        history.getCreatedAt()
                ))
                .toList();
    }
}