package com.amaz.trainingassistantbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {

	private Long id;

	private double calories;

	private LocalDateTime dateOfTraining;

	private LocalTime trainingDurationtos ;
}
