package com.yellowdot.yellowdotapi.dtos;

public record ProductDto(Integer productId, String name, CategoryDto categoryDto, String description, Double price) {
}
