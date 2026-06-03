package com.amaz.trainingassistantbackend.exceptions;

import com.amaz.trainingassistantbackend.security.TrainingAssistantException;

public class AccountAlreadyExistsException extends TrainingAssistantException {
    public AccountAlreadyExistsException(String message) {
        super(409, message);
    }
}
