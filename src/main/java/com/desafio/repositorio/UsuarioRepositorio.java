// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.repositorio;

import com.desafio.modelo.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
}
