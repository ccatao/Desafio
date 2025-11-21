// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.controller;

import com.desafio.dto.SolicitacaoCriarDto;
import com.desafio.servico.SolicitacaoServico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoServico solicitacaoServico;

    public SolicitacaoController(SolicitacaoServico solicitacaoServico) {
        this.solicitacaoServico = solicitacaoServico;
    }

    @PostMapping
    public ResponseEntity<?> criar(@AuthenticationPrincipal(expression = "principal") String usuarioIdStr,
                                   @Valid @RequestBody SolicitacaoCriarDto dto) {
        UUID usuarioId = UUID.fromString(usuarioIdStr);
        String mensagem = solicitacaoServico.criarSolicitacao(usuarioId, dto);
        return ResponseEntity.ok(Map.of("message", mensagem));
    }
}
