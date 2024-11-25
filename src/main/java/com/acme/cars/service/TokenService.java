package com.acme.cars.service;

import com.acme.cars.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    private final String secret = "MY-SUPER-SECRET-1234";
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        return JWT.create()
                .withIssuer("ACME.COM")
                .withSubject(usuario.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .withClaim("email", usuario.getEmail())
                .sign(algorithm);
    }
}