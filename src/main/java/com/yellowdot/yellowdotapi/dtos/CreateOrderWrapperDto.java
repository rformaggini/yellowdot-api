package com.yellowdot.yellowdotapi.dtos;

import java.util.List;

public record CreateOrderWrapperDto(List<OrderItemWrapperDto> dtos) {
}
