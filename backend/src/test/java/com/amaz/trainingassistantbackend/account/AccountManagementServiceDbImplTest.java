package com.amaz.trainingassistantbackend.account;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import com.amaz.trainingassistantbackend.exceptions.AccountAlreadyExistsException;
import com.amaz.trainingassistantbackend.exceptions.AccountDoesntExistException;
import com.amaz.trainingassistantbackend.exceptions.WrongAccountPasswordException;
import com.amaz.trainingassistantbackend.mapper.UserMapper;
import com.amaz.trainingassistantbackend.mapper.UserStatsMapper;
import com.amaz.trainingassistantbackend.repository.UserRepository;
import com.amaz.trainingassistantbackend.repository.UserStatsRepository;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.service.AccountManagementService;
import com.amaz.trainingassistantbackend.service.AccountManagementServiceDbImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountManagementServiceDbImplTest {
    private AccountManagementService accountManagementService;
    private UserRepository userRepository;
    private JwtSecurityManager jwtSecurityManager;
    private List<UserEntity> accounts;
    private UserEntity account;
    private UserStatsMapper userStatsMapper;
    private UserStatsRepository userStatsRepository;
    private UserMapper userMapper;

    @Before
    public void before() {
        accounts = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            accounts.add(new UserEntity());
        }
        account = new UserEntity();
        account.setName("name123");
        account.setPassword("123");

        UserEntity exists = new UserEntity();
        exists.setId(5L);
        exists.setName("exists");
        exists.setPassword(BCrypt.withDefaults().hashToString(12, "pass123".toCharArray()));

        userRepository = mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(accounts);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.of(account));
        when(userRepository.findAccountByName("exists")).thenReturn(Optional.of(exists));
        when(userRepository.findAccountByName(account.getName())).thenReturn(Optional.empty());

        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.idToJwt(5L)).thenReturn("jwt123");

        accountManagementService = new AccountManagementServiceDbImpl(userRepository, jwtSecurityManager,userStatsMapper,userStatsRepository, userMapper);
    }

    @Test
    public void registerAccount() {
        accountManagementService.registerAccount(account);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void registerAccountAlreadyExists() {
        UserEntity account = new UserEntity();
        account.setName("exists");

        accountManagementService.registerAccount(account);
    }

    @Test
    public void loginAccount() {
        String jwt = accountManagementService.loginAccount("exists", "pass123");

        assertEquals("jwt123", jwt);
    }

    @Test(expected = AccountDoesntExistException.class)
    public void loginAccountDoesntExist() {
        accountManagementService.loginAccount("name123", "----");
    }

    @Test(expected = WrongAccountPasswordException.class)
    public void loginAccountWrongPassword() {
        accountManagementService.loginAccount("exists", "bad password");
    }

    @Test
    public void findAll() {
        assertEquals(accounts, accountManagementService.findAll());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findAccountById() {
        assertNull(accountManagementService.findAccountById(1L));
        assertEquals(account, accountManagementService.findAccountById(2L));
    }
}