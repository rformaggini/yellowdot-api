package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.BillCreateDto;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.dtos.PaymentDto;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.services.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAnyAuthority('SCOPE_BASIC','SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<BillDto> createBill(@RequestBody(required = false) BillCreateDto dto) throws FileNotFoundException {
        return ResponseEntity.ok(billService.createBill(dto));
    }

    @PostMapping("/toPay")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<BillDto> payTheBill(@RequestBody PaymentDto dto)  {
        billService.toPay(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/toCancel")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<BillDto> cancelTheBill(@RequestBody Integer billId)  {
        billService.toCancelBill(billId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<BillDto>> getBills(){
        return ResponseEntity.ok(billService.getBills());
    }

    @GetMapping("/getBillOpened")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<List<BillDto>> getBillsOpened(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("SCOPE_ADMIN"));
        if(hasAdminRole){
        return ResponseEntity.ok(billService.getBills());
        } else {
        return ResponseEntity.ok(billService.getAllBillsOpened());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_STAFF')")
    public ResponseEntity<BillDto> getBillById(@PathVariable("id") Integer tableId) throws EntityNotFoundException {
        return ResponseEntity.ok(billService.getBillById(tableId));
    }

    @GetMapping("/getBillByTableNumber/{number}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<BillDto> getBillByTableNumber(@PathVariable("number") Integer number) throws EntityNotFoundException {
        return ResponseEntity.ok(billService.getBillByTableNumber(number));
    }

}
