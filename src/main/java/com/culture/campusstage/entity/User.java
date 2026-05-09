package com.culture.campusstage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String userid;
    private String password;
    private String email;
    private String college;
    private String major;

    @Column(name = "student_year")
    private String studentYear;

    @Column(name = "user_type")
    private String userType;
    private String nickname;
    private LocalDateTime createdAt;

    private String role = "user";
}