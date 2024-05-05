package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
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

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws EntityNotFoundException {
        var categoryToUpdate =  categoryRepository.findById(categoryDto.categoryId());
        if(categoryToUpdate.isEmpty()){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
        categoryToUpdate.get().setName(categoryDto.name());
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryToUpdate.get()));
    }

    @Override
    public void deleteCategory(Integer categoryId) throws EntityNotFoundException {
        var categoryToUpdate =  categoryRepository.findById(categoryId);
        if(categoryToUpdate.isEmpty()){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
        categoryRepository.deleteById(categoryId);
    }


}
