package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
