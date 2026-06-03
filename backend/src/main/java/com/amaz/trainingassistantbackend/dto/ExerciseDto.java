package com.amaz.trainingassistantbackend.dto;

import com.amaz.trainingassistantbackend.utils.enums.BodyParts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BodyParts bodyPart;}
