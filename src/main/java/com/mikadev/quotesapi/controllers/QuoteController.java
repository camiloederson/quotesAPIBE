package com.mikadev.quotesapi.controllers;

import com.mikadev.quotesapi.DTOs.QuoteDTO.QuoteGetDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePostDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePutDTO;
import com.mikadev.quotesapi.services.QuoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotes/api/v1/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<List<QuoteGetDTO>> findAll() {
        List<QuoteGetDTO> quotes = quoteService.findAll();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteGetDTO> findById(@PathVariable Long id) {
        QuoteGetDTO quoteGetDTO = quoteService.findById(id);
        return ResponseEntity.ok(quoteGetDTO);
    }

    @PostMapping
    public ResponseEntity<QuoteGetDTO> save(@Valid @RequestBody QuotePostDTO quotePostDTO) {
        QuoteGetDTO quote = quoteService.create(quotePostDTO);
        URI location = URI.create("/quotes/api/v1/quotes/" + quote.id());
        return ResponseEntity.created(location).body(quote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteGetDTO> update(@PathVariable Long id, @Valid @RequestBody QuotePutDTO quotePutDTO) {
        QuoteGetDTO quote = quoteService.update(id, quotePutDTO);
        return ResponseEntity.ok(quote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quoteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
