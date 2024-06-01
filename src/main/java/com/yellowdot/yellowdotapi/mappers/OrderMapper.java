package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.CreateOrderDto;
import com.yellowdot.yellowdotapi.dtos.OrderDto;
import com.yellowdot.yellowdotapi.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order dtoToEntity(OrderDto dto);
    Order dtoToEntity(CreateOrderDto dto);
    @Mapping(target = "orderId", source = "id")
    OrderDto entityToDto(Order entity);
    List<OrderDto> listEntityToListDto(List<Order> list);
}
