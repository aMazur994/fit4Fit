package com.amaz.trainingassistantbackend.security;

import com.amaz.trainingassistantbackend.StringifyService;
import org.springframework.validation.FieldError;

import java.util.List;

public class TrainingAssistantBeanValidationException extends TrainingAssistantException {
    public TrainingAssistantBeanValidationException(List<FieldError> validationErrors) {
        super(422, "");
        StringifyService stringifyService = new StringifyService();

        setResponseMessage(stringifyService.stringifyValidationErrors(validationErrors));
    }
}
