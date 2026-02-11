package com.mikadev.quotesapi.DTOs.CategoryDTO;

public record CategoryGetDTO(
        Long id,
        String name,
        String description
) {
}
