package com.mikadev.quotesapi.repositories;

import org.springframework.data.jpa.repository.Query;

import com.mikadev.quotesapi.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    QuoteEntity findRandomQuote();
}
