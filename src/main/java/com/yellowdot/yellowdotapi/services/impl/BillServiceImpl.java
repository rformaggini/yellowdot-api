package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.BillCreateDto;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.dtos.PaymentDto;
import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.Order;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.enums.OrderStatus;
import com.yellowdot.yellowdotapi.enums.PaymentStatus;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.mappers.BillMapper;
import com.yellowdot.yellowdotapi.repositories.*;
import com.yellowdot.yellowdotapi.services.BillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final ProductRepository productRepository;
    private final PubTableRepository tableRepository;
    private final FileRepository fileRepository;
    private final OrderRepository orderRepository;

    public BillServiceImpl(BillRepository billRepository, BillMapper billMapper, ProductRepository productRepository, PubTableRepository tableRepository, FileRepository fileRepository, OrderRepository orderRepository) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.productRepository = productRepository;
        this.tableRepository = tableRepository;
        this.fileRepository = fileRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public BillDto createBill(BillCreateDto dto) throws FileNotFoundException {

        var orderCreated = orderRepository.findById(dto.orderId());
        var bill = billMapper.dtoToEntity(dto);

        bill.setStatus(PaymentStatus.OPENED);
        orderCreated.ifPresent(bill::setOrder);

        return billMapper.entityToDto(billRepository.save(bill));
    }

    @Override
    public List<BillDto> getBills() {
        return billMapper.listEntityToListDto(billRepository.findAll());
    }

    @Override
    public List<BillDto> getAllBillsOpened() {
        return billMapper.listEntityToListDto(billRepository.findAllByStatusEquals(PaymentStatus.OPENED));
    }

    @Override
    @Transactional
    public void toPay(PaymentDto dto) {
        var bill = billRepository.findById(dto.billId());
        bill.ifPresent(value -> {
            value.setPaymentMethod(dto.method());
            value.setStatus(PaymentStatus.PAID);
            value.getOrder().setStatus(OrderStatus.CLOSED);
            billRepository.save(value);
            orderRepository.save(value.getOrder());
        });
    }

    @Override
    @Transactional
    public void toCancelBill(Integer billId) {
        var bill = billRepository.findById(billId);
        bill.ifPresent(value -> {
            value.setStatus(PaymentStatus.CLOSED);
            value.getOrder().setStatus(OrderStatus.CLOSED);
            billRepository.save(value);
            orderRepository.save(value.getOrder());
        });
    }

    @Override
    public BillDto getBillByTableNumber(Integer number) throws EntityNotFoundException {
        var tableFromDb = tableRepository.findByNumber(number);
        if(tableFromDb.isEmpty()){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(),MessagesCode.DB001.getCode());
        }
        var billFromDB = billRepository.findByPubTable(tableFromDb.get());

        return billMapper.entityToDto(billFromDB);
    }

    @Override
    public BillDto getBillById(Integer id) throws EntityNotFoundException {

        var billFromDB = billRepository.findById(id);
        if(billFromDB.isEmpty()){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
        return billMapper.entityToDto(billFromDB.get());
    }

    @Override
    public Bill findBillbyOrder(Order order){
        return billRepository.findByOrder(order);
    }
}

