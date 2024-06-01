package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.OrderItemDto;
import com.yellowdot.yellowdotapi.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem dtoToEntity(OrderItemDto dto);
    @Mapping(target = "orderItemId", source = "id")
    OrderItemDto entityToDto(OrderItem entity);
    List<OrderItemDto> listEntityToListDto(List<OrderItem> list);

}
