package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.PaymentMethod;

public record BillDto(String name, String contactNumber, String email, PaymentMethod paymentMethod, Double total) {
}
