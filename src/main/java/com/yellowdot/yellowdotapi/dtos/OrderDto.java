package com.yellowdot.yellowdotapi.dtos;

import java.util.List;

public record OrderDto(Integer orderId, List<OrderItemDto> orderItems) {


}
