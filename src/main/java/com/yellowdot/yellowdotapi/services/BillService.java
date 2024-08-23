package com.yellowdot.yellowdotapi.services;


import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillCreateDto;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BillService {


    BillDto createBill(BillCreateDto dto) throws DocumentException, FileNotFoundException;
    List<BillDto> getBills();
    List<BillDto> getAllBillsOpened();
    BillDto getBillByTableNumber(Integer number) throws EntityNotFoundException;
    BillDto getBillById(Integer id) throws EntityNotFoundException;

}
