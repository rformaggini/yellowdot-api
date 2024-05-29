package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.CreateProductDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateStatusProductDto;
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
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer categoryId) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.getProductById(categoryId));
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable("id") Integer categoryId) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.getAllProductsByCategoryId(categoryId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody CreateProductDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.addNewProduct(dto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductDto> updateStatusProduct(@RequestBody UpdateStatusProductDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.updateStatusProduct(dto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ResponseMessage> deleteProductById(@PathVariable("id") Integer productId) throws EntityNotFoundException {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(new ResponseMessage(MessagesCode.DB002.getCode(), MessagesCode.DB002.getMessage(), false));
    }


}
