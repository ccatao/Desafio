// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.repositorio;

import com.desafio.modelo.entidade.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuloRepositorio extends JpaRepository<Modulo, UUID> {
}
