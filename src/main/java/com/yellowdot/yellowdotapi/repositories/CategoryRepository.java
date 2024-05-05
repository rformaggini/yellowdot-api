package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
}
