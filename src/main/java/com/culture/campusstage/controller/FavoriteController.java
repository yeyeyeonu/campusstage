package com.culture.campusstage.controller;

import com.culture.campusstage.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public void addFavorite(@RequestParam Long userId,
                            @RequestParam Long performanceId) {

        favoriteService.addFavorite(userId, performanceId);
    }
}