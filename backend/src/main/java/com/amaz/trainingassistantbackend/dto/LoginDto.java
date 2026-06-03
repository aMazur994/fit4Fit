package com.amaz.trainingassistantbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "Nazwa jest wymagana")
    @NotEmpty(message = "Nazwa nie może być pusta")
    @NotBlank(message = "Nazwa nie może być znakiem białym")
    private String name;

    @NotNull(message = "Hasło jest wymagane")
    @NotEmpty(message = "Hasło nie może być puste")
    @NotBlank(message = "Hasło nie może być znakiem białym")
    private String password;
}
