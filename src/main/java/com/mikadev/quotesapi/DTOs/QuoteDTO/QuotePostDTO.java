package com.mikadev.quotesapi.DTOs.QuoteDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuotePostDTO(
        @NotBlank(message = "Quote cannot be blank")
        @Size(min = 5, max = 500, message = "Quote must be between 5 and 500 characters")
        String quote,

        @NotNull(message = "Author ID cannot be null")
        Long authorId,

        @NotNull(message = "Category ID cannot be null")
        Long categoryId) {
}