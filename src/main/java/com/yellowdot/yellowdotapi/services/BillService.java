package com.yellowdot.yellowdotapi.services;


import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface BillService {

    String generateReport();
    BillDto createBill(BillDto dto) throws DocumentException, FileNotFoundException;
    List<BillDto> getBills();
    BillDto getBillByTableNumber(Integer number) throws EntityNotFoundException;
    BillDto getBillById(Integer id) throws EntityNotFoundException;
    List<BillDto> getBillByUsername(String username) throws EntityNotFoundException;
    byte[] getBillInPdf(Integer billId);

}
