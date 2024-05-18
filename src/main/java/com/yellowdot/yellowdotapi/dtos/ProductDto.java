package com.yellowdot.yellowdotapi.dtos;

public record ProductDto(Integer productId, String name, CategoryDto category, String description, Double price) {
}
