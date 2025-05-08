package com.stance.EventHub.services;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.stance.EventHub.models.Utilizador;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera um token JWT para o utilizador
    public String generateToken(Utilizador utilizador) {
        return Jwts.builder()
                .setSubject(utilizador.getEmail()) // Define o email como identificador
                .claim("dtype", utilizador.getClass().getSimpleName()) // Adiciona o dtype como uma claim
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration))) // Data de expiração
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assina o token
                .compact();
    }

    // Valida o token e retorna o email do utilizador
    public String validateToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // Retorna o email do utilizador
    }

    // Verifica se o token é válido
    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date()); // Verifica se o token não expirou
        } catch (Exception e) {
            return false;
        }
    }
}