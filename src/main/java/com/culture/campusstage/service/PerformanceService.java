package com.culture.campusstage.service;

import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public List<Performance> getList() {
        return performanceRepository.findAll();
    }
}