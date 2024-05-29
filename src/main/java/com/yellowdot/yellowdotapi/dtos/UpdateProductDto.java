package com.yellowdot.yellowdotapi.dtos;

public record UpdateProductDto(Integer productId, String name, Integer  categoryId, String description, Double price) {
}
