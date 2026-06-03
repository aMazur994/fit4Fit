package com.amaz.trainingassistantbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserStatsDto  {
	private Long height ;

	private Long weight;

	private String bmi;

	private LocalDate dateOfAdd;
}
