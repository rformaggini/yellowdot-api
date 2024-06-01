package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.*;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.infra.ResponseMessage;
import com.yellowdot.yellowdotapi.services.OrderService;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<OrderDto> createOrder() throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.createOrder());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<ResponseMessage<Void>> addProductToOrder(@RequestBody OrderItemWrapperDto orderItemDto) throws EntityNotFoundException {
        orderService.addProductToOrder(orderItemDto);
        return ResponseEntity.ok(new ResponseMessage<>("DB-004", "Products added successfuly", false));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<ResponseMessage<Void>> deleteOrder(@PathVariable("id") Integer orderId) throws Exception {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/order/{orderId}/product/{productId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<ResponseMessage<Void>> deleteProductsFromOrder(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("productId") Integer productId ) throws EntityNotFoundException {
        orderService.deleteProductsFromOrder(orderId, productId);
        return ResponseEntity.ok().build();
    }


}
