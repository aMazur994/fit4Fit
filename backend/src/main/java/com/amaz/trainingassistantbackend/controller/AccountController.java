package com.amaz.trainingassistantbackend.controller;

import com.amaz.trainingassistantbackend.dto.LoginDto;
import com.amaz.trainingassistantbackend.dto.UserDto;
import com.amaz.trainingassistantbackend.dto.UserStatsDto;
import com.amaz.trainingassistantbackend.dto.UserStatsReqDto;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.security.TrainingAssistantBeanValidationException;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.service.AccountManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {
    private final AccountManagementService accountManagementService;
    private final AccountSecurityService accountSecurityService;
    private final JwtSecurityManager jwtSecurityManager;

    @PostMapping("/accounts")
    public void registerAccount(@RequestBody @Valid UserEntity account, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new TrainingAssistantBeanValidationException(validationResult.getFieldErrors());
        }

        accountManagementService.registerAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity loginAccount(@RequestBody @Valid LoginDto loginDto, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new TrainingAssistantBeanValidationException(validationResult.getFieldErrors());
        }
        String jwt = accountManagementService.loginAccount(loginDto.getName(), loginDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-auth", jwt);
        httpHeaders.set("Access-Control-Expose-Headers", "x-auth");

        return new ResponseEntity<>(
                "",
                httpHeaders,
                HttpStatus.OK
        );
    }

    @GetMapping("/account")
    public ResponseEntity<UserDto> getAccount(@RequestHeader(value = "x-auth", required = false) String jwt) {
        long accountId = jwtSecurityManager.jwtToId(jwt);
        accountSecurityService.checkIfAccountIsValidById(accountId);

        return ResponseEntity.ok(accountManagementService.findAccountById(accountId));
    }

    @PostMapping("/statsAdd")
    public void saveUserStats(@RequestHeader(value = "x-auth", required = false) String jwt,@RequestBody @Valid UserStatsReqDto dto, BindingResult validationResult){
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);
        long accountId = jwtSecurityManager.jwtToId(jwt);
        if(validationResult.hasErrors()) {
            throw new TrainingAssistantBeanValidationException(validationResult.getFieldErrors());
        }
        accountManagementService.saveUserStats(dto, accountId);
    }
    @GetMapping("/stats")
    public ResponseEntity <UserStatsDto> getUserStats(@RequestHeader(value = "x-auth", required = false) String jwt){
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);
        long accountId = jwtSecurityManager.jwtToId(jwt);

        return ResponseEntity.ok().body(accountManagementService.getLastUserStats(accountId));
    }
}
