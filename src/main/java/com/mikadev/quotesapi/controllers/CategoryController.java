package com.mikadev.quotesapi.controllers;

import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryGetDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPostDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPutDTO;
import com.mikadev.quotesapi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotes/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryGetDTO>> getCategories() {
        List<CategoryGetDTO> categoriesList = categoryService.findAll();
        return ResponseEntity.ok(categoriesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryGetDTO> getCategory(@PathVariable long id) {
        CategoryGetDTO categoryGetDTO = categoryService.findById(id);
        return ResponseEntity.ok(categoryGetDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryGetDTO> createCategory(@Valid @RequestBody CategoryPostDTO categoryPostDTO) {
        CategoryGetDTO createdCategory = categoryService.createCategory(categoryPostDTO);
        URI location = URI.create("/quotes/api/v1/categories" + createdCategory.id());
        return ResponseEntity.created(location).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryGetDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryPutDTO categoryPutDTO) {
        CategoryGetDTO categoryGetDTO = categoryService.updateCategory(id, categoryPutDTO);
        return ResponseEntity.ok(categoryGetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
