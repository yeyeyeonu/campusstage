package com.culture.campusstage.repository;

import com.culture.campusstage.entity.Review;
import com.culture.campusstage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUser(User user);
}