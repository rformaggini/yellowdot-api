package com.yellowdot.yellowdotapi.services;


import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BillService {


    BillDto createBill(BillDto dto) throws DocumentException, FileNotFoundException;
    List<BillDto> getBills();
    BillDto getBillByTableNumber(Integer number) throws EntityNotFoundException;
    BillDto getBillById(Integer id) throws EntityNotFoundException;
    byte[] getBillInPdf(Integer billId) throws EntityNotFoundException, IOException;

}
