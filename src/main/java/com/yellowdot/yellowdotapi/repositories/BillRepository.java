package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.PubTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Bill findByPubTable(PubTable table);
    //List<Bill> findByUsername(String username);


}
