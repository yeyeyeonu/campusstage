package com.culture.campusstage.repository;

import com.culture.campusstage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);
    boolean existsByUserid(String userid);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}