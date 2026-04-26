package com.culture.campusstage.service;

import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.repository.PerformanceDetailRepository;
import com.culture.campusstage.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.culture.campusstage.entity.PerformanceDetail;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceDetailRepository performanceDetailRepository;

    public List<Performance> getList() {
        return performanceRepository.findAll();
    }

    public List<Performance> getByCategory(Long categoryId) {
        return performanceRepository.findByCategoryId(categoryId);

    }
}