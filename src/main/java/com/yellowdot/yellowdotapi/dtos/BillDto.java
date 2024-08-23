package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.PaymentMethod;
import com.yellowdot.yellowdotapi.enums.PaymentStatus;

public record BillDto(String name, String contactNumber, String email, PaymentMethod paymentMethod, PaymentStatus status, OrderDto order) {
}
