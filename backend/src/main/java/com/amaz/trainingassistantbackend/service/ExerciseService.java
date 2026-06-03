package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.dto.ExerciseDto;
import com.amaz.trainingassistantbackend.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseDto exercise);
    void deleteExercise(long id);
    List<ExerciseEntity> findAllExercises();
}
