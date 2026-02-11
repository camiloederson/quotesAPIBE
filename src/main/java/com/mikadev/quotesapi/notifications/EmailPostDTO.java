package com.mikadev.quotesapi.notifications;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EmailPostDTO(
        @NotBlank
        @Email
        String from,

        @NotBlank
        List<@Email String> to) {
}
