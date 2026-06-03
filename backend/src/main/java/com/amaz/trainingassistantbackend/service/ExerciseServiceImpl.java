package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.dto.ExerciseDto;
import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import com.amaz.trainingassistantbackend.exceptions.ExerciseAlreadyExistException;
import com.amaz.trainingassistantbackend.exceptions.ExerciseDoesntExistException;
import com.amaz.trainingassistantbackend.mapper.ExerciseMapper;
import com.amaz.trainingassistantbackend.repository.ExerciseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final AccountEmailNotificationService accountEmailNotificationService;
    private final ExerciseMapper exerciseMapper;

    @Override
    public void addExercise(ExerciseDto exercise) {
        if(!exerciseRepository.getExerciseByName(exercise.getName()).isEmpty()) {
            throw new ExerciseAlreadyExistException("Już zapisano ćwiczenie o tej nazwie");
        }
        exerciseRepository.save(exerciseMapper.mapToEntity(exercise));
    }

    @Override
    public void deleteExercise(long id) {
        if(!exerciseRepository.findById(id).isPresent()) {
            throw new ExerciseDoesntExistException("Ćwiczenie o identyfikatorze równym " + id + " nie istnieje!");
        }
        exerciseRepository.deleteById(id);
    }

    @Override
    public List<ExerciseEntity> findAllExercises() {
        List<ExerciseEntity> exerciseEntities = exerciseRepository.findAll();
        exerciseEntities.sort(Comparator.comparing(ExerciseEntity::getBodyPart));
        return exerciseEntities;
    }

}
