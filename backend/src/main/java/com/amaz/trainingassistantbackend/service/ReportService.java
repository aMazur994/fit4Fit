package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import com.amaz.trainingassistantbackend.report.ShelterReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ExerciseService exerciseService;

    public ShelterReport getShelterReport() {
        List<ExerciseEntity> entities = exerciseService.findAllExercises();
        int maxAnimals = 5;

        return new ShelterReport(entities, maxAnimals);
    }
}
