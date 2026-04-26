package com.culture.campusstage.repository;

import com.culture.campusstage.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    List<Performance> findByCategoryId(Long categoryId);
}