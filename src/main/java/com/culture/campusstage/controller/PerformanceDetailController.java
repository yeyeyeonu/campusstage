package com.culture.campusstage.controller;

import com.culture.campusstage.entity.PerformanceDetail;
import com.culture.campusstage.service.PerformanceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PerformanceDetailController {

    private final PerformanceDetailService performanceDetailService;

    @GetMapping("/performance-details")
    public List<PerformanceDetail> getPerformanceDetails() {
        return performanceDetailService.getList();
    }
}