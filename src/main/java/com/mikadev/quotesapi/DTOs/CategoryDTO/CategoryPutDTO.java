package com.mikadev.quotesapi.DTOs.CategoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryPutDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(min = 25, max = 250, message = "Description must be between 25 and 250 characters")
        String description
) {
}
