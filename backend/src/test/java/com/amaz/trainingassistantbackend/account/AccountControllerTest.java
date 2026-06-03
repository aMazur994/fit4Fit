package com.amaz.trainingassistantbackend.account;

import com.amaz.trainingassistantbackend.controller.AccountController;
import com.amaz.trainingassistantbackend.dto.LoginDto;
import com.amaz.trainingassistantbackend.dto.UserDto;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import com.amaz.trainingassistantbackend.mapper.UserMapper;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.service.AccountManagementService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest {
    private AccountController accountController;
    private AccountManagementService accountManagementService;
    private JwtSecurityManager jwtSecurityManager;
    private UserMapper userMapper;
    private LoginDto loginDto;
    private UserEntity account;
    private UserDto acc;

    @Before
    public void before() {
        loginDto = new LoginDto();
        loginDto.setName("user123");
        loginDto.setPassword("password123");

        account = new UserEntity();
        account.setName("user123");
        acc = userMapper.mapToDto(account);

        accountManagementService = mock(AccountManagementService.class);
        when(accountManagementService.loginAccount(loginDto.getName(), loginDto.getPassword())).thenReturn("jwt123");
        when(accountManagementService.findAccountById(5L)).thenReturn(acc);

        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.jwtToId("jwt123")).thenReturn(5L);

        accountController = new AccountController(accountManagementService, mock(AccountSecurityService.class), jwtSecurityManager);
    }

    @Test
    public void registerAccount() {
        accountController.registerAccount(account, mock(BindingResult.class));

        verify(accountManagementService, times(1)).registerAccount(account);
    }

    @Test
    public void loginAccount() {
        ResponseEntity responseEntity = accountController.loginAccount(loginDto, mock(BindingResult.class));

        verify(accountManagementService, times(1)).loginAccount(loginDto.getName(), loginDto.getPassword());
        assertEquals("jwt123", Objects.requireNonNull(responseEntity.getHeaders().get("x-auth")).get(0));
        assertEquals("x-auth", Objects.requireNonNull(responseEntity.getHeaders().get("Access-Control-Expose-Headers")).get(0));
        assertEquals("", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAccount() {
        UserDto account = accountController.getAccount("jwt123").getBody();

        assertEquals("user123", account.getName());
    }
}