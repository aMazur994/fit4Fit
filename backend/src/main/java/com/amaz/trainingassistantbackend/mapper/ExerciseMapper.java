package com.amaz.trainingassistantbackend.mapper;

import com.amaz.trainingassistantbackend.dto.ExerciseDto;
import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {

    public ExerciseEntity mapToEntity(ExerciseDto dto) {
        return ExerciseEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .bodyPart(dto.getBodyPart())
                .build();
    }

    public ExerciseDto mapToDto(ExerciseEntity entity) {
        return ExerciseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .bodyPart(entity.getBodyPart())
                .build();
    }
}
