package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Integer> {
    @Override
    Optional<File> findById(Integer integer);

    Optional<File> findByBillId(Integer billId);
}
