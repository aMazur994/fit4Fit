package com.amaz.trainingassistantbackend.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfiguration {
    @Bean
    public JwtSecurityManager jwtSecurityManager() {
        SecretKey jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return new JwtSecurityManager(jwtSecretKey);
    }
}
