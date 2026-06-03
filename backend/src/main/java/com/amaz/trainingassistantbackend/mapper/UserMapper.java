package com.amaz.trainingassistantbackend.mapper;

import com.amaz.trainingassistantbackend.dto.UserDto;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	public UserDto mapToDto(UserEntity entity){
		return UserDto.builder()
				.id(entity.getId())
				.name(entity.getName())
				.email(entity.getEmail())
				.base64Image(entity.getBase64Image())
				.dateOfAdd(entity.getDateOfAdd())
				.build();
	}
}
