package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.dto.TrainingDto;

import java.util.List;

public interface TrainingService {
	void addTraining(Long minutes, Long accoutId);

	List<TrainingDto> getTrainingFromDatePeriod(Long accoutId);
}
