package com.yellowdot.yellowdotapi.controllers;

import com.itextpdf.text.DocumentException;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.services.BillService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<List<BillDto>> getBills(){
        return ResponseEntity.ok(billService.getBills());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<BillDto> getBillById(@PathVariable("id") Integer tableId) throws EntityNotFoundException {
        return ResponseEntity.ok(billService.getBillById(tableId));
    }

    @GetMapping("/getBillByTableNumber/{number}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF', 'SCOPE_COSTUMER')")
    public ResponseEntity<BillDto> getBillByTableNumber(@PathVariable("number") Integer number) throws EntityNotFoundException {
        return ResponseEntity.ok(billService.getBillByTableNumber(number));
    }

    /*@GetMapping("/getBillByUsername/{username}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF', 'SCOPE_COSTUMER')")
    public ResponseEntity<List<BillDto>> getBillByUsername(@PathVariable("username") String username) throws EntityNotFoundException {
        return ResponseEntity.ok(billService.getBillByUsername(username));
    }*/

    @GetMapping("/getBillInPdf")
    public ResponseEntity<byte[]> getBillInPdf(){
        return ResponseEntity.ok().build();
    }



}
