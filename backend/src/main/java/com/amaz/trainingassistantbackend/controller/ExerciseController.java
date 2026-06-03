package com.amaz.trainingassistantbackend.controller;

import com.amaz.trainingassistantbackend.dto.ExerciseDto;
import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.service.ExerciseService;
import com.amaz.trainingassistantbackend.security.TrainingAssistantBeanValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final AccountSecurityService accountSecurityService;

    @PostMapping("/exercise")
    public void addExercise(@RequestHeader(value = "x-auth", required = false) String jwt,
                            @RequestBody @Valid ExerciseDto exercise, BindingResult validationResult) {
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);
        if(validationResult.hasErrors()) {
            throw new TrainingAssistantBeanValidationException(validationResult.getFieldErrors());
        }
        exerciseService.addExercise(exercise);
    }

    @GetMapping("/exercises")
    public ResponseEntity <List<ExerciseEntity>> getAllExercises(){
        return ResponseEntity.ok(exerciseService.findAllExercises());
    }

    @DeleteMapping("/exercise/{id}")
    public void deleteExercise(@RequestHeader(value = "x-auth", required = false) String jwt, @PathVariable("id") long id) {
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);

        exerciseService.deleteExercise(id);
    }
}
