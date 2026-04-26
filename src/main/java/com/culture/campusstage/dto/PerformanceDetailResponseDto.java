package com.culture.campusstage.dto;

import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.entity.PerformanceDetail;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PerformanceDetailResponseDto {

    private Long id;
    private String name;
    private String posterUrl;
    private String location;
    private LocalDateTime performanceDate;
    private String description;
    private String status;
    private String detailInfo;

    public PerformanceDetailResponseDto(Performance performance, PerformanceDetail detail) {
        this.id = performance.getId();
        this.name = performance.getName();
        this.posterUrl = performance.getPosterUrl();
        this.location = performance.getLocation();
        this.performanceDate = performance.getPerformanceDate();
        this.description = performance.getDescription();
        this.status = performance.getStatus();
        this.detailInfo = detail.getDetailInfo();
    }
}