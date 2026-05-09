package com.culture.campusstage.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {

    private String userid;
    private String password;
    private String name;
    private String nickname;
    private String email;

    private String college;
    private String major;
    private String studentYear;
    private String userType;
}