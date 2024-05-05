package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto addNewCategory(CategoryDto categoryDto);
}
