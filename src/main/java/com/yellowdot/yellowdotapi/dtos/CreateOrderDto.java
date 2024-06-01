package com.yellowdot.yellowdotapi.dtos;

import java.util.List;

public record CreateOrderDto(List<OrderItemDto> orderItems) {
}
