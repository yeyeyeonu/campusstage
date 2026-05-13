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

    // 테이블이 두개로 나눠져 있기 때문에 하나의 Dto로 묶어서 보여주기 위함
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