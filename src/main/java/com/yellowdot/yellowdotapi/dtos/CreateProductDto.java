package com.yellowdot.yellowdotapi.dtos;

public record CreateProductDto(String name, Integer  categoryId, String description, Double price) {
}
