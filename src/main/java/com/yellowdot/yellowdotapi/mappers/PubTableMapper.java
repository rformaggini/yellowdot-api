package com.yellowdot.yellowdotapi.mappers;

import com.yellowdot.yellowdotapi.dtos.PubTableCreateDto;
import com.yellowdot.yellowdotapi.dtos.PubTableDto;
import com.yellowdot.yellowdotapi.entities.PubTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PubTableMapper {

    PubTable dtoToEntity(PubTableDto dto);
    PubTable dtoToEntity(PubTableCreateDto dto);
    List<PubTableDto> listEntityToListDto(List<PubTable> list);
    PubTableDto entityToDto(PubTable entity);
}
