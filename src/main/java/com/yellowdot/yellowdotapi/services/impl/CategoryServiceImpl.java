package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import com.yellowdot.yellowdotapi.entities.Category;
import com.yellowdot.yellowdotapi.mappers.CategoryMapper;
import com.yellowdot.yellowdotapi.repositories.CategoryRepository;
import com.yellowdot.yellowdotapi.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.listEntityToListDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto addNewCategory(CategoryDto dto) {
        var newCategory = categoryRepository.save(categoryMapper.categoryDtoToCategory(dto));
        return categoryMapper.categoryToCategoryDto(newCategory);
    }
}
