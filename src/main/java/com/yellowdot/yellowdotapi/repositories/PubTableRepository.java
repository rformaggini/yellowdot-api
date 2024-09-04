package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.PubTable;
import com.yellowdot.yellowdotapi.enums.PubTableStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PubTableRepository extends JpaRepository<PubTable, Integer> {

    Optional<PubTable> findByNumber(Integer number);
    List<PubTable> findAllByStatus(PubTableStatus status);

}
