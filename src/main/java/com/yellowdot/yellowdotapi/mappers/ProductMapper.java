package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.CreateProductDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductDto dto);

    @Mapping(target = "category", ignore = true)
    Product dtoToEntityWithoutCategory(CreateProductDto dto);

    @Mapping(target = "category", source = "category")
    ProductDto entityToDto(Product entity);

    List<Product> listDtoToListEntity(List<ProductDto> dtoList);
    List<ProductDto> listEntityToListDto(List<Product> list);
}
