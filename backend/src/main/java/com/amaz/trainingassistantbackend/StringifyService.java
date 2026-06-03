package com.amaz.trainingassistantbackend;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;

public class StringifyService {
    private final StringBuilder stringBuilder;

    public StringifyService() {
        stringBuilder = new StringBuilder();
    }

    public String stringifyValidationErrors(List<FieldError> errors) {
        stringBuilder.append("Validation error!\n");
        errors.forEach(error -> stringBuilder.append(error.getDefaultMessage()).append('\n'));

        return stringBuilder.toString();
    }
}
