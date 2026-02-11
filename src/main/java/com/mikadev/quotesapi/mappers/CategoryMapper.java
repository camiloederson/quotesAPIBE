package com.mikadev.quotesapi.mappers;

import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryGetDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPostDTO;
import com.mikadev.quotesapi.DTOs.CategoryDTO.CategoryPutDTO;
import com.mikadev.quotesapi.entities.CategoryEntity;

public class CategoryMapper {

    public static CategoryGetDTO toCategoryGetDTO(CategoryEntity categoryEntity) {
        return new CategoryGetDTO(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getDescription()
        );
    }

    public static CategoryEntity toCategoryEntity(CategoryPostDTO categoryPostDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPostDTO.name());
        categoryEntity.setDescription(categoryPostDTO.description());
        return categoryEntity;
    }

    public static CategoryEntity toUpdateCategoryEntity(CategoryPutDTO categoryPutDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPutDTO.name());
        categoryEntity.setDescription(categoryPutDTO.description());
        return categoryEntity;
    }
}
