package com.culture.campusstage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteResponseDto {

    private Long performanceId;
    private String performanceName;
    private String posterUrl;
}