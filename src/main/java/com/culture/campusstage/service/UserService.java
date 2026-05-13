package com.culture.campusstage.service;

import com.culture.campusstage.dto.LoginRequestDto;
import com.culture.campusstage.dto.LoginResponseDto;
import com.culture.campusstage.dto.SignupRequestDto;
import com.culture.campusstage.entity.User;
import com.culture.campusstage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // (1) 회원가입 처리 함수
    // Dto 로 처리를 하고 -> 나중에 Entity로 변환
    public void signup(SignupRequestDto request) {

        if (userRepository.existsByUserid(request.getUserid())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        // DB에 저장할 user Entity
        User user = new User();

        // DTO -> Entity 값 복사
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

    // (2) 로그인 처리
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByUserid(request.getUserid())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // Dto로 필요한 값만 담아서 전달
        return new LoginResponseDto(
                user.getId(),
                user.getUserid(),
                user.getName(),
                user.getNickname()
        );
    }

    // (3) 아이디 중복 확인
    public boolean checkUseridAvailable(String userid) {
        return !userRepository.existsByUserid(userid);
    }

    // (4) 닉네임 중복 확인
    public boolean checkNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }
}