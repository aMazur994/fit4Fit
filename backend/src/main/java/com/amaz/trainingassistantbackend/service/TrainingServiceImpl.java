package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.dto.TrainingDto;
import com.amaz.trainingassistantbackend.entity.TrainingEntity;
import com.amaz.trainingassistantbackend.entity.UserStatsEntity;
import com.amaz.trainingassistantbackend.mapper.TrainingMapper;
import com.amaz.trainingassistantbackend.repository.TrainingRepository;
import com.amaz.trainingassistantbackend.repository.UserRepository;
import com.amaz.trainingassistantbackend.repository.UserStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService{

	private final TrainingMapper trainingMapper;
	private final TrainingRepository trainingRepository;
	private final UserRepository userRepository;
	private final UserStatsRepository userStatsRepository;

	@Override
	public void addTraining(Long minutes, Long  accountId) {
		TrainingDto dto = new TrainingDto();
		setDurationToTrainingDto(dto, minutes);
		UserStatsEntity userStats = userStatsRepository.findLastStats(accountId);
		setKcal(dto, userStats);
		TrainingEntity entity = trainingMapper.mapToEntity(dto, accountId);
		trainingMapper.mapToDto(trainingRepository.save(entity));
	}

	@Override
	public List<TrainingDto> getTrainingFromDatePeriod(Long accountId) {
		List<TrainingEntity> entities = trainingRepository.getByAccountId(accountId);
		return entities
				.stream()
				.sorted(Comparator.comparing(TrainingEntity::getTrainingDate))
				.map(trainingMapper::mapToDto)
				.collect(Collectors.toList());
	}

	private void setDurationToTrainingDto(TrainingDto dto, Long minutes){
		LocalTime duration = LocalTime.of(0, (int) (minutes % 60)); // Get minutes part
		duration = duration.plusHours(minutes / 60);
		dto.setTrainingDurationtos(duration);
	}

	private void setKcal(TrainingDto dto, UserStatsEntity entity){
		double met = 7.0;
		dto.setCalories( met * entity.getWeight() * createDurationTime(dto.getTrainingDurationtos()));
	}

	private double createDurationTime(LocalTime time){
		return time.getHour()+ (double) time.getMinute() /60;
	}
}
