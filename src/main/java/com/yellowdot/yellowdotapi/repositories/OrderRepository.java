package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Order;
import com.yellowdot.yellowdotapi.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByStatus(OrderStatus orderStatus);
}
