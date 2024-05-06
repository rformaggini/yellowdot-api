package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.CreateProductDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.infra.ResponseMessage;
import com.yellowdot.yellowdotapi.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllCategories(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.addNewProduct(dto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ResponseMessage> deleteProductById(@PathVariable("id") Integer categoryId) throws EntityNotFoundException {
        productService.deleteProduct(categoryId);
        return ResponseEntity.ok(new ResponseMessage(MessagesCode.DB002.getCode(), MessagesCode.DB002.getMessage(), false));
    }
}
