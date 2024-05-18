package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.entities.Bill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill dtoToEntity(BillDto dto);
    BillDto entityToDto(Bill entity);
    List<Bill> listDtoToListEntity(List<BillDto> dtoList);
    List<BillDto> listEntityToListDto(List<Bill> list);

}
