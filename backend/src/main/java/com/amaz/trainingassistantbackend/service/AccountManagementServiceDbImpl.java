package com.amaz.trainingassistantbackend.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.amaz.trainingassistantbackend.dto.UserDto;
import com.amaz.trainingassistantbackend.dto.UserStatsDto;
import com.amaz.trainingassistantbackend.dto.UserStatsReqDto;
import com.amaz.trainingassistantbackend.entity.UserEntity;
import com.amaz.trainingassistantbackend.entity.UserStatsEntity;
import com.amaz.trainingassistantbackend.exceptions.AccountAlreadyExistsException;
import com.amaz.trainingassistantbackend.exceptions.AccountDoesntExistException;
import com.amaz.trainingassistantbackend.exceptions.WrongAccountPasswordException;
import com.amaz.trainingassistantbackend.mapper.UserMapper;
import com.amaz.trainingassistantbackend.mapper.UserStatsMapper;
import com.amaz.trainingassistantbackend.repository.UserRepository;
import com.amaz.trainingassistantbackend.repository.UserStatsRepository;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManagementServiceDbImpl implements AccountManagementService {
    private final UserRepository userRepository;
    private final JwtSecurityManager jwtSecurityManager;
    private final UserStatsMapper userStatsMapper;
    private final UserStatsRepository userStatsRepository;
    private final UserMapper userMapper;

    public static final String STATS_NOT_FOUND = "validate.statsNotFound";

    @Override
    public void registerAccount(UserEntity account) {
        if(userRepository.findAccountByName(account.getName()).isPresent()) {
            throw new AccountAlreadyExistsException("Pracownik \"" + account.getName() + "\" już jest zarejestrowany!");
        }

        String bcryptedPassword = BCrypt.withDefaults()
                .hashToString(12, account.getPassword().toCharArray());
        account.setPassword(bcryptedPassword);

        userRepository.save(account);
    }

    @Override
    public String loginAccount(String name, String password) {
        Optional<UserEntity> account = userRepository.findAccountByName(name);

        if(!account.isPresent()) {
            throw new AccountDoesntExistException("Użytkownik \"" + name + "\" nie istnieje!");
        }

        BCrypt.Result comparsionPasswoResult = BCrypt.verifyer()
                .verify(password.toCharArray(), account.get().getPassword());
        if(comparsionPasswoResult.verified) {
            // correct credentials ==> log in ==> create JWT
            return jwtSecurityManager.idToJwt(account.get().getId());
        } else {
            throw new WrongAccountPasswordException("Złe hasło!");
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findAccountById(Long id) {
        return userMapper.mapToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserStatsDto saveUserStats(UserStatsReqDto dto, Long accountId) {
        UserStatsEntity entity = userStatsMapper.mapToEntity(dto);
        entity.setUser(userRepository.findById(accountId).orElse(null));
        userStatsRepository.save(entity);
        return userStatsMapper.mapToDto(entity);
    }

    @Override
    public UserStatsDto getLastUserStats(Long accountId) {
		UserStatsEntity entity =  Optional.ofNullable(userStatsRepository.findLastStats(accountId))
                .orElse(new UserStatsEntity());
        return userStatsMapper.mapToDto(entity);

    }

}
