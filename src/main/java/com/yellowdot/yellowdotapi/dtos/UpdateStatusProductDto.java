package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.ProductStatus;

public record UpdateStatusProductDto(Integer productId, ProductStatus status) {
}
