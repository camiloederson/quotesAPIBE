package com.mikadev.quotesapi.repositories;

import com.mikadev.quotesapi.entities.AuthorEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    boolean existsByFirstNameAndSecondNameAndSurname(String firstName, String secondName, String surname);
}
