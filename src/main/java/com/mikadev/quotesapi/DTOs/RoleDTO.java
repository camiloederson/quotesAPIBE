package com.mikadev.quotesapi.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleDTO(
        Long id,
        @NotBlank
        @Size(min = 3, max = 50)
        String roleName,
        @NotBlank
        @Size(max = 200)
        String description
) {
}
