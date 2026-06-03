package com.amaz.trainingassistantbackend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericShelterControllerAdvice {
    @ExceptionHandler(TrainingAssistantException.class)
    public ResponseEntity<?> genericShelterBrainExceptionHandler(TrainingAssistantException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getResponseMessage());
    }
}
