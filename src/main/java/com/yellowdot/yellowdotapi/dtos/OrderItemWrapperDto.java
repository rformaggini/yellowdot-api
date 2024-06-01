package com.yellowdot.yellowdotapi.dtos;

import java.util.Optional;

public record OrderItemWrapperDto(Integer quantity, Integer orderId, Integer productId) {

        Optional<Integer> getOrderId() {
            return Optional.ofNullable(orderId());
        }

        public OrderItemWrapperDto withOrderId(Integer orderId){
            return new OrderItemWrapperDto(quantity(), orderId, productId());
        }
}
