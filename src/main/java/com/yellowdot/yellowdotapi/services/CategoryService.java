package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import com.yellowdot.yellowdotapi.dtos.CreateCategoryDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto addNewCategory(CreateCategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto) throws EntityNotFoundException;
    void deleteCategory(Integer categoryId) throws EntityNotFoundException;

}
