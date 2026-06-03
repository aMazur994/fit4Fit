package com.amaz.trainingassistantbackend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TrainingAssistantException extends RuntimeException {
    private int httpStatus;
    private String responseMessage;
}
