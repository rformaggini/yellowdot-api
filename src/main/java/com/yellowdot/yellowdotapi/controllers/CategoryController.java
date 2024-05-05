package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.infra.ResponseMessage;
import com.yellowdot.yellowdotapi.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CategoryDto> addNewCategory(@RequestBody CategoryDto dto){
        return ResponseEntity.ok(categoryService.addNewCategory(dto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto) throws EntityNotFoundException {
        return ResponseEntity.ok(categoryService.updateCategory(dto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ResponseMessage> deleteCategoryById(@PathVariable("id") Integer categoryId) throws EntityNotFoundException {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ResponseMessage(MessagesCode.DB002.getCode(), MessagesCode.DB002.getMessage(), false));
    }


}
