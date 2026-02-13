package com.mikadev.quotesapi.services;

import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryGetDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPostDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPutDTO;
import com.mikadev.quotesapi.entities.CategoryEntity;
import com.mikadev.quotesapi.exceptions.ResourceAlreadyExistsException;
import com.mikadev.quotesapi.exceptions.ResourceNotFoundException;
import com.mikadev.quotesapi.mappers.CategoryMapper;
import com.mikadev.quotesapi.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryGetDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryGetDTO)
                .toList();
    }

    public CategoryGetDTO findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return CategoryMapper.toCategoryGetDTO(categoryEntity);
    }

    public CategoryGetDTO createCategory(CategoryPostDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.name())) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }
        CategoryEntity categorySaved = categoryRepository.save(CategoryMapper.toCategoryEntity(categoryDTO));
        return CategoryMapper.toCategoryGetDTO(categorySaved);
    }

    public CategoryGetDTO updateCategory(Long id, CategoryPutDTO categoryDTO) {
        CategoryEntity categoryToUpdate = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        CategoryEntity categoryUpdated = categoryRepository.save(CategoryMapper.toUpdateCategoryEntity(categoryDTO));
        return CategoryMapper.toCategoryGetDTO(categoryUpdated);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
