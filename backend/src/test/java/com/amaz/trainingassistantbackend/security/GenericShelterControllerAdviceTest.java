package com.amaz.trainingassistantbackend.security;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class GenericShelterControllerAdviceTest {
    @Test
    public void genericShelterBrainExceptionHandler() {
        GenericShelterControllerAdvice genericShelterControllerAdvice = new GenericShelterControllerAdvice();

        ResponseEntity responseEntity = genericShelterControllerAdvice.genericShelterBrainExceptionHandler(new TrainingAssistantException(400, "responseMessage"));

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("responseMessage", responseEntity.getBody());
    }
}