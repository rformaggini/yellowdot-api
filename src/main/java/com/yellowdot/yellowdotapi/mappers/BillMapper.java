package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.BillCreateDto;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.entities.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill dtoToEntity(BillDto dto);
    Bill dtoToEntity(BillCreateDto dto);
    @Mapping(target = "order.orderId", source = "order.id")
    BillDto entityToDto(Bill entity);
    List<Bill> listDtoToListEntity(List<BillDto> dtoList);
    List<BillDto> listEntityToListDto(List<Bill> list);

}
