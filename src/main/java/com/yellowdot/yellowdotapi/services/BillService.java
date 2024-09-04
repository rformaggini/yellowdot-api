package com.yellowdot.yellowdotapi.services;


import com.yellowdot.yellowdotapi.dtos.BillCreateDto;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.dtos.PaymentDto;
import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.Order;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface BillService {


    BillDto createBill(BillCreateDto dto) throws FileNotFoundException;
    List<BillDto> getBills();
    List<BillDto> getAllBillsOpened();
    void toPay(PaymentDto dto);
    void toCancelBill(Integer billId);
    BillDto getBillByTableNumber(Integer number) throws EntityNotFoundException;
    BillDto getBillById(Integer id) throws EntityNotFoundException;
    Bill findBillbyOrder(Order order);

}
