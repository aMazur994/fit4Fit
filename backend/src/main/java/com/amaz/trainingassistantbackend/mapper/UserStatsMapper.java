package com.amaz.trainingassistantbackend.mapper;

import com.amaz.trainingassistantbackend.dto.UserStatsDto;
import com.amaz.trainingassistantbackend.dto.UserStatsReqDto;
import com.amaz.trainingassistantbackend.entity.UserStatsEntity;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Component
public class UserStatsMapper {

	DecimalFormat decimalFormat = new DecimalFormat("#.##");

	public UserStatsDto mapToDto(UserStatsEntity entity){
		return UserStatsDto.builder()
				.height(entity.getHeight())
				.weight(entity.getWeight())
				.bmi(entity.getBmi())
				.dateOfAdd(entity.getDateOfAdd().toLocalDate())
				.build();
	}

	public UserStatsEntity mapToEntity(UserStatsReqDto dto){
		UserStatsDto statsDto = UserStatsDto.builder()
				.weight(dto.getWeight())
				.height(dto.getHeight())
				.build();
		return UserStatsEntity.builder()
				.height(statsDto.getHeight())
				.weight(statsDto.getWeight())
				.bmi(createBmi(statsDto.getWeight(), statsDto.getHeight()))
				.dateOfAdd(LocalDateTime.now())
				.build();
	}

	private String createBmi(Long weight, Long height){
		return decimalFormat.format((double) weight /((double) (height * height) /10000));
	}
}
