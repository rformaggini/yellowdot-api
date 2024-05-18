package com.yellowdot.yellowdotapi.services;


import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillDto;

import java.io.FileNotFoundException;

public interface BillService {

    String generateReport();
    BillDto createBill(BillDto dto) throws DocumentException, FileNotFoundException;

}
