// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.repositorio;

import com.desafio.modelo.entidade.AcessoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcessoUsuarioRepositorio extends JpaRepository<AcessoUsuario, UUID> {
    int countByUsuarioIdAndAtivoTrue(UUID usuarioId);
    boolean existsByUsuarioIdAndModuloIdAndAtivoTrue(UUID usuarioId, UUID moduloId);
}
