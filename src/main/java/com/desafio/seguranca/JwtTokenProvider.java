// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.segredo}")
    private String segredo;

    @Value("${jwt.expiracao-minutos}")
    private long expiracaoMinutos;

    private Key chave() {
        return Keys.hmacShaKeyFor(segredo.getBytes());
    }

    public String gerarToken(String usuarioId, String email) {
        Instant agora = Instant.now();
        Date expiracao = Date.from(agora.plus(Duration.ofMinutes(expiracaoMinutos)));
        return Jwts.builder()
                .setSubject(usuarioId)
                .claim("email", email)
                .setIssuedAt(Date.from(agora))
                .setExpiration(expiracao)
                .signWith(chave())
                .compact();
    }

    public Jws<Claims> validarToken(String token) {
        return Jwts.parserBuilder().setSigningKey(chave()).build().parseClaimsJws(token);
    }
}
