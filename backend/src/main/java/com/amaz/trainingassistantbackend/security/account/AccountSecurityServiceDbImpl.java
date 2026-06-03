package com.amaz.trainingassistantbackend.security.account;

import com.amaz.trainingassistantbackend.repository.UserRepository;
import com.amaz.trainingassistantbackend.security.JwtSecurityManager;
import com.amaz.trainingassistantbackend.security.NotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountSecurityServiceDbImpl implements AccountSecurityService {
    private final JwtSecurityManager jwtSecurityManager;
    private final UserRepository userRepository;

    @Override
    public void checkIfAccountIsValidByJwt(String jwt) {
        checkIfAccountIsValidById(jwtSecurityManager.jwtToId(jwt));
    }

    @Override
    public void checkIfAccountIsValidById(long id) {
        if(!userRepository.findById(id).isPresent()) {
            throw new NotAuthenticatedException();
        }
    }
}
