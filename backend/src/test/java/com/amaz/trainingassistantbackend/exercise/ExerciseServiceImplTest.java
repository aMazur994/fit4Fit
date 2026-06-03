package com.amaz.trainingassistantbackend.exercise;

import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import com.amaz.trainingassistantbackend.exceptions.ExerciseDoesntExistException;
import com.amaz.trainingassistantbackend.mapper.ExerciseMapper;
import com.amaz.trainingassistantbackend.service.AccountEmailNotificationService;
import com.amaz.trainingassistantbackend.repository.ExerciseRepository;
import com.amaz.trainingassistantbackend.service.ExerciseService;
import com.amaz.trainingassistantbackend.service.ExerciseServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ExerciseServiceImplTest {
    private ExerciseService exerciseService;
    private ExerciseRepository exerciseRepository;
    private AccountEmailNotificationService accountEmailNotificationService;
    private ExerciseMapper exerciseMapper;

    @Before
    public void before() {
        exerciseMapper = mock(ExerciseMapper.class);
        exerciseRepository = mock(ExerciseRepository.class);
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());
        when(exerciseRepository.findById(2L)).thenReturn(Optional.of(new ExerciseEntity()));

        List<ExerciseEntity> exerciseEntities = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            exerciseEntities.add(new ExerciseEntity());
        }
        when(exerciseRepository.findAll()).thenReturn(exerciseEntities);

        accountEmailNotificationService = mock(AccountEmailNotificationService.class);

        exerciseService = new ExerciseServiceImpl( exerciseRepository, accountEmailNotificationService,exerciseMapper);
    }

  /* @Test(expected = ExerciseAlreadyExistException.class)
  /* public void addAnimalShelterIsFullException() {
        exerciseService.addExercise(new ExerciseEntity());
    }*/

    //@Test
    //public void addAnimalShelterHasPlace() {
    //    exerciseRepository = mock(ExerciseRepository.class);
    //    when(exerciseRepository.findAll()).thenReturn(new LinkedList<>());
//
    //    exerciseService = new ExerciseServiceImpl(5, exerciseRepository, accountEmailNotificationService);
//
    //    ExerciseEntity exercise = new ExerciseEntity();
    //    exercise.setName("testAnimal");
//
    //    exerciseService.addExercise(exercise);
//
    //    verify(exerciseRepository, times(1)).save(exercise);
    //    //verify(accountEmailNotificationService, times(1)).sendWarningEmailsIfNecessary();
    //}

    @Test(expected = ExerciseDoesntExistException.class)
    public void deleteAnimalDoesntExistException() {
        exerciseService.deleteExercise(1L);
    }

    @Test
    public void deleteAnimalExists() {
        exerciseService.deleteExercise(2L);

        verify(exerciseRepository, times(1)).deleteById(2L);
    }
}