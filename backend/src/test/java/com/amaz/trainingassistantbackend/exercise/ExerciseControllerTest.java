package com.amaz.trainingassistantbackend.exercise;

import com.amaz.trainingassistantbackend.dto.ExerciseDto;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.controller.ExerciseController;
import com.amaz.trainingassistantbackend.security.NotAuthenticatedException;
import com.amaz.trainingassistantbackend.service.ExerciseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class ExerciseControllerTest {
    private ExerciseController exerciseController;
    private ExerciseService exerciseService;
    private AccountSecurityService accountSecurityService;

    @Before
    public void before() {
        exerciseService = mock(ExerciseService.class);
        accountSecurityService = mock(AccountSecurityService.class);
        exerciseController = new ExerciseController(exerciseService, accountSecurityService);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void jwtSecurity() {
        accountSecurityService = mock(AccountSecurityService.class);
        doThrow(NotAuthenticatedException.class).when(accountSecurityService).checkIfAccountIsValidByJwt(any(String.class));

        exerciseController = new ExerciseController(exerciseService, accountSecurityService);

        exerciseController.addExercise("x", new ExerciseDto(), mock(BindingResult.class));
        exerciseController.deleteExercise("x", 1L);
    }

    @Test
    public void addExercise() {
        ExerciseDto exercise = new ExerciseDto();

        exerciseController.addExercise("x", exercise, mock(BindingResult.class));

        verify(exerciseService, times(1)).addExercise(exercise);
    }

    @Test
    public void deleteExercise() {
        exerciseController.deleteExercise("x", 1L);

        verify(exerciseService, times(1)).deleteExercise(1L);
    }
}