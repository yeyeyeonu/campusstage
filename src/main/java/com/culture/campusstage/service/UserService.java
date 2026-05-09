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
        user.setCollege(request.getCollege());
        user.setMajor(request.getMajor());
        user.setStudentYear(request.getStudentYear());
        user.setUserType(request.getUserType());

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

    public boolean checkUseridAvailable(String userid) {
        return !userRepository.existsByUserid(userid);
    }

    public boolean checkNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }
}