package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.OrderDto;
import com.yellowdot.yellowdotapi.dtos.OrderItemWrapperDto;
import com.yellowdot.yellowdotapi.entities.OrderItem;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();
    OrderDto createOrder() throws EntityNotFoundException;
    void addProductToOrder(OrderItemWrapperDto orderItemDto) throws EntityNotFoundException;
    void deleteProductsFromOrder(Integer orderId, Integer productId) throws EntityNotFoundException;
    void deleteOrder(Integer orderId) throws Exception;


}
