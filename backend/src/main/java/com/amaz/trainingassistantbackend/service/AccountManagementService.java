package com.amaz.trainingassistantbackend.service;

import com.amaz.trainingassistantbackend.dto.UserDto;
import com.amaz.trainingassistantbackend.dto.UserStatsDto;
import com.amaz.trainingassistantbackend.dto.UserStatsReqDto;
import com.amaz.trainingassistantbackend.entity.UserEntity;

import java.util.List;

public interface AccountManagementService {
    void registerAccount(UserEntity account);

    /**
     * Returns encoded JWT with logged in account's ID
     */
    String loginAccount(String name, String password);

    List<UserEntity> findAll();
    UserDto findAccountById(Long id);
    UserStatsDto saveUserStats(UserStatsReqDto dto, Long accountId);

    UserStatsDto getLastUserStats(Long accountId);
}
