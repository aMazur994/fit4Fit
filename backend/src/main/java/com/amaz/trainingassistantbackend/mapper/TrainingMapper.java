package com.amaz.trainingassistantbackend.mapper;

import com.amaz.trainingassistantbackend.dto.TrainingDto;
import com.amaz.trainingassistantbackend.entity.TrainingEntity;
import com.amaz.trainingassistantbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

	private final UserRepository userRepository;
	public TrainingEntity mapToEntity(TrainingDto dto, Long accountId) {
		return TrainingEntity.builder()
				.id(dto.getId())
				.calories(dto.getCalories())
				.trainingDate(LocalDateTime.now())
				.trainingDuration(dto.getTrainingDurationtos())
				.user(userRepository.getReferenceById(accountId))
				.build();
	}

	public TrainingDto mapToDto(TrainingEntity entity) {
		return TrainingDto.builder()
				.id(entity.getId())
				.calories(entity.getCalories())
				.dateOfTraining(entity.getTrainingDate())
				.trainingDurationtos(entity.getTrainingDuration())
				.build();
	}
}
