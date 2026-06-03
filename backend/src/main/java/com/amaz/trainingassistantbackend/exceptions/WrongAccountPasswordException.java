package com.amaz.trainingassistantbackend.exceptions;

import com.amaz.trainingassistantbackend.security.TrainingAssistantException;

public class WrongAccountPasswordException extends TrainingAssistantException {
    public WrongAccountPasswordException(String message) {
        super(401, message);
    }
}
