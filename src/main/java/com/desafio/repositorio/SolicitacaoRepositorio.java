// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.repositorio;

import com.desafio.modelo.entidade.SolicitacaoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SolicitacaoRepositorio extends JpaRepository<SolicitacaoAcesso, UUID> {
    Optional<SolicitacaoAcesso> findByProtocolo(String protocolo);
}
