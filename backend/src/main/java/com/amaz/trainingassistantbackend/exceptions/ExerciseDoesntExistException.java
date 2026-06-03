package com.amaz.trainingassistantbackend.exceptions;

import com.amaz.trainingassistantbackend.security.TrainingAssistantException;

public class ExerciseDoesntExistException extends TrainingAssistantException {
    public ExerciseDoesntExistException(String message) {
        super(404, message);
    }
}
