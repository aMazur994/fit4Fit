package com.amaz.trainingassistantbackend;

import com.amaz.trainingassistantbackend.security.TrainingAssistantBeanValidationException;
import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.Assert.*;

public class StringifyServiceTest {
    @Test
    public void stringifyValidationErrors() {
        List<FieldError> validationErrors = List.of(
                new FieldError("x", "y", "message1"),
                new FieldError("a", "b", "message2")
        );
        TrainingAssistantBeanValidationException exception = new TrainingAssistantBeanValidationException(validationErrors);

        assertEquals(422, exception.getHttpStatus());
        assertEquals("Validation error!\nmessage1\nmessage2\n", exception.getResponseMessage());
    }
}