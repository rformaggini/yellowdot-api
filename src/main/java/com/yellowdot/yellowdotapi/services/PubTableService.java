package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.dtos.PubTableCreateDto;
import com.yellowdot.yellowdotapi.dtos.PubTableDto;

import java.util.List;

public interface PubTableService {

    PubTableDto create(PubTableCreateDto dto);
    List<PubTableDto> getAll();
    void deletePubTable(Integer id);

}
