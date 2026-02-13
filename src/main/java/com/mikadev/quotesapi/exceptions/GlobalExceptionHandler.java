package com.mikadev.quotesapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                Instant.now(),
                404,
                "NotFound",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleResourceAlreadyExists(
            ResourceAlreadyExistsException ex,
            HttpServletRequest request
    ){
        ApiError apiError = new ApiError(
                Instant.now(),
                409,
                "Already Exist",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(409).body(apiError);
    }

}
