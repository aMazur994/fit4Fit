package com.amaz.trainingassistantbackend.exceptions;

import com.amaz.trainingassistantbackend.security.TrainingAssistantException;

public class AccountDoesntExistException extends TrainingAssistantException {
    public AccountDoesntExistException(String message) {
        super(404, message);
    }
}
