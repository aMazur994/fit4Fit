package com.amaz.trainingassistantbackend.controller;


import com.amaz.trainingassistantbackend.dto.TrainingDto;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.security.TrainingAssistantBeanValidationException;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class TrainingController {

	private final TrainingService trainingService;
	private final AccountSecurityService accountSecurityService;
	private final JwtSecurityManager jwtSecurityManager;


	@PostMapping("/training")
	public void addTraining(@RequestHeader(value = "x-auth", required = false) String jwt, @RequestBody MinutesRequestDto  minutes, BindingResult validationResult) {
		accountSecurityService.checkIfAccountIsValidByJwt(jwt);
		long accountId = jwtSecurityManager.jwtToId(jwt);
		if(validationResult.hasErrors()) {
			throw new TrainingAssistantBeanValidationException(validationResult.getFieldErrors());
		}
		trainingService.addTraining(Math.round(minutes.getMinutes()),accountId);
	}

	@GetMapping("/training")
	public List<TrainingDto> getTrainingList(@RequestHeader(value = "x-auth", required = false) String jwt){
		accountSecurityService.checkIfAccountIsValidByJwt(jwt);
		long accountId = jwtSecurityManager.jwtToId(jwt);

		return trainingService.getTrainingFromDatePeriod(accountId);
	}
}
