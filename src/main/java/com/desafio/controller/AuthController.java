// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.controller;

import com.desafio.model.entidade.Usuario;
import com.desafio.repository.UsuarioRepository;
import com.desafio.seguranca.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String senha = body.get("senha");

        Optional<Usuario> opt = usuarioRepository.findByEmail(email);

        if (opt.isEmpty())
            return ResponseEntity.status(401).body(Map.of("error", "Usuário ou senha inválidos"));

        Usuario u = opt.get();

        if (!passwordEncoder.matches(senha, u.getSenhaHash()))
            return ResponseEntity.status(401).body(Map.of("error", "Usuário ou senha inválidos"));

        String token = jwtTokenProvider.gerarToken(u.getId().toString(), u.getEmail());
        return ResponseEntity.ok(Map.of("accessToken", token));
    }
}
