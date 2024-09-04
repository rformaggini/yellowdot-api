package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.PubTableCreateDto;
import com.yellowdot.yellowdotapi.dtos.PubTableDto;
import com.yellowdot.yellowdotapi.enums.PubTableStatus;
import com.yellowdot.yellowdotapi.mappers.PubTableMapper;
import com.yellowdot.yellowdotapi.repositories.PubTableRepository;
import com.yellowdot.yellowdotapi.services.PubTableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubTableServiceImpl implements PubTableService {

    private final PubTableRepository tableRepository;
    private final PubTableMapper tableMapper;

    public PubTableServiceImpl(PubTableRepository tableRepository, PubTableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;
    }

    @Override
    public PubTableDto create(PubTableCreateDto dto) {
        var newTable = tableMapper.dtoToEntity(dto);
        newTable.setStatus(PubTableStatus.ACTIVE);
        return tableMapper.entityToDto(tableRepository.save(newTable));
    }

    @Override
    public List<PubTableDto> getAll() {
        return tableMapper.listEntityToListDto(tableRepository.findAllByStatus(PubTableStatus.ACTIVE));
    }

    @Override
    public void deletePubTable(Integer id) {
        var pubtable = tableRepository.findById(id);
        pubtable.ifPresent(table -> table.setStatus(PubTableStatus.INACTIVE));
        pubtable.ifPresent(tableRepository::save);
    }
}
