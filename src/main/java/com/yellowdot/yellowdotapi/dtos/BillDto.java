package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.PaymentMethod;

import java.util.List;

public record BillDto(String name, String contactNumber, String email, PaymentMethod paymentMethod, Double total) {
}
