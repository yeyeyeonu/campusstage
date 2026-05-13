package com.culture.campusstage.service;

import com.culture.campusstage.entity.Favorite;
import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.entity.User;
import com.culture.campusstage.repository.FavoriteRepository;
import com.culture.campusstage.repository.PerformanceRepository;
import com.culture.campusstage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PerformanceRepository performanceRepository;

    public void addFavorite(Long userId, Long performanceId) {

        boolean exists =
                favoriteRepository.existsByUserIdAndPerformanceId(userId, performanceId);

        if (exists) {
            return;
        }

        User user = userRepository.findById(userId)
                .orElseThrow();

        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow();

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setPerformance(performance);
        favorite.setCreatedAt(LocalDateTime.now());

        favoriteRepository.save(favorite);
    }
}