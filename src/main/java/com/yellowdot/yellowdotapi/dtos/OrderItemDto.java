package com.yellowdot.yellowdotapi.dtos;

public record OrderItemDto(Integer orderItemId, ProductDto product, Integer quantity) {
}
