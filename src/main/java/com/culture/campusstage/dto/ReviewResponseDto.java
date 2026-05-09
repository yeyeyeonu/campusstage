package com.culture.campusstage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;
    private Long performanceId;
    private String performanceName;
    private String content;
    private LocalDateTime createdAt;
}