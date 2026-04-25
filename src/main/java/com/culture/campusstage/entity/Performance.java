package com.culture.campusstage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "performances")
@Getter
@Setter
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 공연명

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String location;

    private LocalDateTime performanceDate;

    @Column(nullable = false)
    private String status = "active";

    @Column(columnDefinition = "TEXT")
    private String description;

    private String posterUrl;

    private LocalDateTime createdAt;
}