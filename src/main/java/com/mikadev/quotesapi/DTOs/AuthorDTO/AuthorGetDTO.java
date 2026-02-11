package com.mikadev.quotesapi.DTOs.AuthorDTO;

public record AuthorGetDTO(
        Long id,
        String firstName,
        String secondName,
        String surname) {
}
