package com.amaz.trainingassistantbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.amaz.trainingassistantbackend.utils.Pattern.emailPattern;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

	private Long id;

	@NotNull(message = "Nazwa jest wymagana")
	@NotEmpty(message = "Nazwa nie może być pusta")
	@NotBlank(message = "Nazwa nie może być znakiem białym")
	private String name;

	@NotNull(message = "Hasło jest wymagane")
	@NotEmpty(message = "Hasło nie może być puste")
	@NotBlank(message = "Hasło nie może być znakiem białym")
	private String password;

	@NotNull(message = "E-mail jest wymagany")
	@NotEmpty(message = "E-mail nie może być pusty")
	@NotBlank(message = "E-mail nie może być znakiem białym")
	@Pattern(regexp = emailPattern)
	private String email;

	private String base64Image;

	private LocalDateTime dateOfAdd;
}
