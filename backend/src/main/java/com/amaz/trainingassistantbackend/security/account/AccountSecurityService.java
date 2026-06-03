package com.amaz.trainingassistantbackend.security.account;

public interface AccountSecurityService {
    void checkIfAccountIsValidByJwt(String jwt);
    void checkIfAccountIsValidById(long id);
}
