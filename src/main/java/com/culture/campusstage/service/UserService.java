package com.culture.campusstage.service;

import com.culture.campusstage.dto.LoginRequestDTO;
import com.culture.campusstage.dto.SignupRequestDTO;
import com.culture.campusstage.entity.User;
import com.culture.campusstage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequestDTO request) {

        if (userRepository.existsByUserid(request.getUserid())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        User user = new User();

        user.setName(request.getName());
        user.setUserid(request.getUserid());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());

        userRepository.save(user);
    }

    public boolean login(LoginRequestDTO request) {

        User user = userRepository.findByUserid(request.getUserid())
                .orElse(null);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(request.getPassword());
    }
}