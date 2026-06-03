package com.amaz.trainingassistantbackend.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;

import static org.junit.Assert.*;

public class JwtSecurityManagerTest {
    private JwtSecurityManager jwtSecurityManager;

    @Before
    public void before() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        jwtSecurityManager = new JwtSecurityManager(key);
    }

    @Test
    public void idJwtExchange() {
        long id = 5L;

        String jwt = jwtSecurityManager.idToJwt(id);

        assertEquals(id, jwtSecurityManager.jwtToId(jwt));
    }

    @Test(expected = NotAuthenticatedException.class)
    public void jwtSignatureViolation() {
        String jwt = jwtSecurityManager.idToJwt(5L);
        jwt += "0x1234";

        jwtSecurityManager.jwtToId(jwt);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void jwtToIdNullException() {
        jwtSecurityManager.jwtToId(null);
    }
}