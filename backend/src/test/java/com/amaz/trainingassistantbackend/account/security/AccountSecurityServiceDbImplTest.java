package com.amaz.trainingassistantbackend.account.security;

import com.amaz.trainingassistantbackend.security.account.AccountSecurityService;
import com.amaz.trainingassistantbackend.security.account.AccountSecurityServiceDbImpl;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import com.amaz.trainingassistantbackend.repository.UserRepository;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.security.NotAuthenticatedException;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountSecurityServiceDbImplTest {
    private AccountSecurityService accountSecurityService;
    private JwtSecurityManager jwtSecurityManager;
    private UserRepository userRepository;

    @Before
    public void before() {
        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.jwtToId("exception")).thenReturn(1L);
        when(jwtSecurityManager.jwtToId("ok")).thenReturn(2L);

        userRepository = mock(UserRepository.class);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.of(new UserEntity()));

        accountSecurityService = new AccountSecurityServiceDbImpl(jwtSecurityManager, userRepository);
    }

    @Test
    public void checkIfAccountIsValidByJwt() {
        accountSecurityService.checkIfAccountIsValidByJwt("ok");
    }

    @Test(expected = NotAuthenticatedException.class)
    public void checkIfAccountIsValidByJwtException() {
        accountSecurityService.checkIfAccountIsValidByJwt("exception");
    }

    @Test
    public void checkIfAccountIsValidById() {
        accountSecurityService.checkIfAccountIsValidById(2L);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void checkIfAccountIsValidByIdException() {
        accountSecurityService.checkIfAccountIsValidById(1L);
    }
}