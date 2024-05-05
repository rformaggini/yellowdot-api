package com.yellowdot.yellowdotapi.controllers;

import com.yellowdot.yellowdotapi.dtos.CategoryDto;
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
    private ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    @PreAuthorize("hasAuthorite('ADMIN')")
    private ResponseEntity<CategoryDto> addNewCategory(@RequestBody CategoryDto dto){
        return ResponseEntity.ok(categoryService.addNewCategory(dto));
    }


}
