package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.OrderDto;
import com.yellowdot.yellowdotapi.dtos.OrderItemWrapperDto;
import com.yellowdot.yellowdotapi.entities.Order;
import com.yellowdot.yellowdotapi.entities.OrderItem;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.mappers.OrderMapper;
import com.yellowdot.yellowdotapi.repositories.OrderItemRepository;
import com.yellowdot.yellowdotapi.repositories.OrderRepository;
import com.yellowdot.yellowdotapi.repositories.ProductRepository;
import com.yellowdot.yellowdotapi.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.listEntityToListDto(orderRepository.findAll());
    }

    @Override
    public OrderDto createOrder() throws EntityNotFoundException {
        var orderSaved = orderRepository.save(new Order());
        return orderMapper.entityToDto(orderSaved);
    }

    @Override
    public void addProductToOrder(OrderItemWrapperDto orderItemDto) throws EntityNotFoundException {


        var order = orderRepository.findById(orderItemDto.orderId());
        var product = productRepository.findById(orderItemDto.productId());
        order.ifPresent(
                value -> {
                    if(value.getOrderItems().stream().anyMatch(
                            orderItem ->  orderItem.getProduct().getProductId().equals(product.get().getProductId())
                    )){

                        value.getOrderItems().forEach(orderItem -> {

                            if (orderItem.getProduct().getProductId().equals(product.get().getProductId())) {
                                orderItem.setQuantity(orderItemDto.quantity());
                                orderItemRepository.save(orderItem);
                            }
                        });
                    } else {
                        orderItemRepository.save(new OrderItem(value, product.get(), orderItemDto.quantity()));
                    }
                });

    }

    @Override
    public void deleteProductsFromOrder(Integer orderId, Integer productId) throws EntityNotFoundException {
        var orderFromDb = orderRepository.findById(orderId);
        AtomicReference<OrderItem> orderItemToDelete = new AtomicReference<>(new OrderItem());
        orderFromDb.ifPresent(order -> order.getOrderItems()
            .forEach(
                item -> {
                    if(item.getProduct().getProductId().equals(productId)){
                        orderItemToDelete.set(item);
                    }
                }
        ));
        if(orderItemToDelete.get() != null){
            var orderItem = orderItemRepository.findById(orderItemToDelete.get().getId());
            orderItemRepository.delete(orderItem.get());
        }
    }

    @Override
    public void deleteOrder(Integer orderId) throws Exception {
        try {
        orderRepository.deleteById(orderId);
        } catch (Exception ex){
            throw new Exception("Something went wrong while trying to delete order");
        }
    }


}
