package com.amenor.openclassrooms.msauthserv.exception;

import com.amenor.openclassrooms.msauthserv.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthException {

    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<?> handleAuthFailedException(AuthFailedException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().field("Auth").cause(exception.getMessage()).build();
        return ResponseEntity.status(401).body(errorResponse);
    }

    public static class AuthFailedException extends RuntimeException {
        public AuthFailedException(String message) {super(message);}
    }
}

