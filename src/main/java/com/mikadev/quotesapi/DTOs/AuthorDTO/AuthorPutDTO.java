package com.mikadev.quotesapi.DTOs.AuthorDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorPutDTO(
        @NotBlank(message = "First name cannot be blank")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @Size(max = 50, message = "Second name must be at most 50 characters")
        String secondName,

        @Size(max = 50, message = "Surname must be at most 50 characters")
        String surname
) {
}
