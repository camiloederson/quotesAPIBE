package com.mikadev.quotesapi.DTOs.QuoteDTO;

public record QuoteGetDTO(
        Long id,

        String quote,

        Long authorId,

        String authorName,

        Long categoryId,

        String categoryName) {
}