package com.culture.campusstage.repository;

import com.culture.campusstage.entity.PerformanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerformanceDetailRepository extends JpaRepository<PerformanceDetail, Long> {

    Optional<PerformanceDetail> findByPerformanceId(Long performanceId);
}