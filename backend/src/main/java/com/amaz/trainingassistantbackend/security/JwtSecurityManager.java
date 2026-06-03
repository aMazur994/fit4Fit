package com.amaz.trainingassistantbackend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import java.security.Key;

@RequiredArgsConstructor
public class JwtSecurityManager {
    private final Key key;

    public String idToJwt(long id) {
        return Jwts.builder()
                .setSubject(Long.toString(id))
                .signWith(key)
                .compact();
    }

    public long jwtToId(String jwt) {
        if(jwt == null) {
            throw new NotAuthenticatedException();
        }

        try {
            String id = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
            return Long.parseLong(id);
        } catch(JwtException exc) {
            throw new NotAuthenticatedException();
        }
    }
}
