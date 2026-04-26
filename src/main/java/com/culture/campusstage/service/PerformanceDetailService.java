package com.culture.campusstage.service;

import com.culture.campusstage.dto.PerformanceDetailResponseDto;
import com.culture.campusstage.entity.Performance;
import com.culture.campusstage.entity.PerformanceDetail;
import com.culture.campusstage.repository.PerformanceDetailRepository;
import com.culture.campusstage.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceDetailService {

    private final PerformanceDetailRepository performanceDetailRepository;
    private final PerformanceRepository performanceRepository;
    public List<PerformanceDetail> getList() {
        return performanceDetailRepository.findAll();
    }

    public PerformanceDetail getByPerformanceId(Long performanceId) {
        return performanceDetailRepository.findByPerformanceId(performanceId)
                .orElseThrow(() -> new RuntimeException("공연 상세 정보가 없습니다."));
    }

    public PerformanceDetailResponseDto getDetailPage(Long performanceId) {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new RuntimeException("공연이 없습니다."));

        PerformanceDetail detail = performanceDetailRepository.findByPerformanceId(performanceId)
                .orElseThrow(() -> new RuntimeException("공연 상세 정보가 없습니다."));

        return new PerformanceDetailResponseDto(performance, detail);
    }
}