package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto addNewProduct(ProductDto dto) throws EntityNotFoundException;
    ProductDto updateProduct(ProductDto dto) throws EntityNotFoundException;
    void deleteProduct(Integer productId) throws EntityNotFoundException;
}