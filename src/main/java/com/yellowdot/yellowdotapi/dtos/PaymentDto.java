package com.yellowdot.yellowdotapi.dtos;

import com.yellowdot.yellowdotapi.enums.PaymentMethod;

public record PaymentDto(Integer billId, PaymentMethod method) {
}
