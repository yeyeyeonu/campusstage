package com.culture.campusstage.service;

import com.culture.campusstage.entity.PerformanceDetail;
import com.culture.campusstage.repository.PerformanceDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceDetailService {

    private final PerformanceDetailRepository performanceDetailRepository;

    public List<PerformanceDetail> getList() {
        return performanceDetailRepository.findAll();
    }
}