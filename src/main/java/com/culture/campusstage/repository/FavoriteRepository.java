package com.culture.campusstage.repository;

import com.culture.campusstage.entity.Favorite;
import com.culture.campusstage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);
}