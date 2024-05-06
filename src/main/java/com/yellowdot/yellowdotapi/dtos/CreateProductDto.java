package com.yellowdot.yellowdotapi.dtos;

public record CreateProductDto(Integer productId, String name, Integer  categoryId, String description, Double price) {
}
