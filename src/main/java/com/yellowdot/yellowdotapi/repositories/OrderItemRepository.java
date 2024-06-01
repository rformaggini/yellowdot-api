package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
