package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import com.yellowdot.yellowdotapi.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category dtoToEntity(CategoryDto dto);
    CategoryDto entityToDto(Category entity);
    List<Category> listDtoToListEntity(List<CategoryDto> dtoList);
    List<CategoryDto> listEntityToListDto(List<Category> list);


}
