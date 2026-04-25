package com.culture.campusstage.controller;

import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping("/performances")
    public List<Performance> getPerformances() {
        return performanceService.getList();
    }
}