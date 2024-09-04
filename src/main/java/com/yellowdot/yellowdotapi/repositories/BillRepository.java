package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.Order;
import com.yellowdot.yellowdotapi.entities.PubTable;
import com.yellowdot.yellowdotapi.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Bill findByPubTable(PubTable table);
    List<Bill> findAllByStatusEquals(PaymentStatus status);
    Bill findByOrder(Order order);

}
