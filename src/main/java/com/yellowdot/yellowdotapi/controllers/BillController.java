package com.yellowdot.yellowdotapi.controllers;

import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.services.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/open")
    @PreAuthorize("hasAnyAuthority('SCOPE_BASIC','SCOPE_ADMIN', 'SCOPE_STAFF', 'SCOPE_COSTUMER')")
    public ResponseEntity<BillDto> createBill(@RequestBody BillDto dto) throws DocumentException, FileNotFoundException {
        return ResponseEntity.ok(billService.createBill(dto));
    }

}
