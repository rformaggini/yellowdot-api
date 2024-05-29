package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.CreateProductDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateStatusProductDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto addNewProduct(CreateProductDto dto) throws EntityNotFoundException;
    ProductDto updateProduct(UpdateProductDto dto) throws EntityNotFoundException;
    void deleteProduct(Integer productId) throws EntityNotFoundException;
    ProductDto updateStatusProduct(UpdateStatusProductDto dto) throws EntityNotFoundException;
    List<ProductDto> getAllProductsByCategoryId(Integer categoryId) throws EntityNotFoundException;
    ProductDto getProductById(Integer categoryId) throws EntityNotFoundException;
}
