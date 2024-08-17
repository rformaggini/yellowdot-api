package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    //@Query("DELETE FROM OrderItem WHERE order.id=:orderId AND product.id=:productId")
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( value = "DELETE FROM tb_order_item WHERE order_id=:orderId AND product_id=:productId", nativeQuery = true)
    void deleteOrderItemByOrderIdAndProductId(Integer orderId, Integer productId);
}
