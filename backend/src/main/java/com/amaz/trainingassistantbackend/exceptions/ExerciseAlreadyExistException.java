package com.amaz.trainingassistantbackend.exceptions;

import com.amaz.trainingassistantbackend.security.TrainingAssistantException;

public class ExerciseAlreadyExistException extends TrainingAssistantException {
    public ExerciseAlreadyExistException(String message) {
        super(409, message);
    }
}
