package com.amaz.trainingassistantbackend.security;

public class NotAuthenticatedException extends TrainingAssistantException {
    public NotAuthenticatedException() {
        super(401, "JWT security violation!");
    }
}
