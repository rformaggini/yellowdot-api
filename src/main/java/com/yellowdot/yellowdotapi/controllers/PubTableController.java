package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.*;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.services.BillService;
import com.yellowdot.yellowdotapi.services.PubTableService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/table")
public class PubTableController {

    private final PubTableService pubTableService;

    public PubTableController(PubTableService pubTableService) {
        this.pubTableService = pubTableService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<PubTableDto> createTable(@RequestBody PubTableCreateDto dto) {
        return ResponseEntity.ok(pubTableService.create(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<List<PubTableDto>> getAllTables()  {
        return ResponseEntity.ok(pubTableService.getAll());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_STAFF')")
    public ResponseEntity<Void> cancelTable(@PathVariable("id") Integer tableId)  {
        pubTableService.deletePubTable(tableId);
        return ResponseEntity.ok().build();
    }
}
