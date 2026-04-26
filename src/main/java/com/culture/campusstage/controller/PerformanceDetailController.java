package com.culture.campusstage.controller;

import com.culture.campusstage.dto.PerformanceDetailResponseDto;
import com.culture.campusstage.entity.PerformanceDetail;
import com.culture.campusstage.service.PerformanceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PerformanceDetailController {

    private final PerformanceDetailService performanceDetailService;


    @GetMapping("/performances/detail")
    public List<PerformanceDetail> getList() {
        return performanceDetailService.getList();
    }

    @GetMapping("/performances/detail/{performanceId}")
    public PerformanceDetailResponseDto getDetailPage(@PathVariable Long performanceId) {
        return performanceDetailService.getDetailPage(performanceId);
    }
}