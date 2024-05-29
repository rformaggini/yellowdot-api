package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.ProductStatus;

public record ProductDto(Integer productId, String name, CategoryDto category, String description, Double price,
                         ProductStatus status) {
}
