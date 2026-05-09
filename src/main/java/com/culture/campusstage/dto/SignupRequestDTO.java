package com.culture.campusstage.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupRequestDTO {
    private String name;
    private String userid;
    private String password;
    private String email;
    private String nickname;
}
