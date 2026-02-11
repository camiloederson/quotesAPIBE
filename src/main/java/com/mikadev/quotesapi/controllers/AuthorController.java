package com.mikadev.quotesapi.controllers;

import com.mikadev.quotesapi.DTOs.AuthorDTO.AuthorGetDTO;
import com.mikadev.quotesapi.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotes/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorGetDTO>> findAll() {
        List<AuthorGetDTO> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorGetDTO> findById(@PathVariable Long id) {
        AuthorGetDTO authorGetDTO = authorService.findById(id);
        return ResponseEntity.ok(authorGetDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorGetDTO> save(@Valid @RequestBody AuthorGetDTO authorGetDTO) {
        AuthorGetDTO author = authorService.create(authorGetDTO);
        URI location = URI.create("/quotes/api/v1/authors" + author.id());
        return ResponseEntity.created(location).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorGetDTO> update(@PathVariable Long id, @Valid @RequestBody AuthorGetDTO authorGetDTO) {
        AuthorGetDTO author = authorService.update(id, authorGetDTO);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
