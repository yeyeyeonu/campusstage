package com.culture.campusstage.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class WatchHistoryResponseDto {

    private Long performanceId;
    private String performanceName;
    private String posterUrl;
    private LocalDateTime watchedAt;
}