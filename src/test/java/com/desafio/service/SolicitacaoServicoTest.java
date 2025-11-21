// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.service;

import com.desafio.dto.SolicitacaoCriarDto;
import com.desafio.model.entidade.Usuario;
import com.desafio.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitacaoServicoTest {

    @Mock private UsuarioRepository usuarioRepository;
    @Mock private ModuloRepository moduloRepository;
    @Mock private AcessoUsuarioRepository acessoUsuarioRepository;
    @Mock private SolicitacaoRepository solicitacaoRepository;

    @InjectMocks private SolicitacaoServico servico;

    @Test
    void criarSolicitacao_negarQuandoLimiteAtingido() {
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setDepartamento("OUTROS");

        when(usuarioRepository.findById(eq(usuarioId))).thenReturn(Optional.of(usuario));
        when(acessoUsuarioRepository.countByUsuarioIdAndAtivoTrue(eq(usuarioId))).thenReturn(5);

        SolicitacaoCriarDto dto = new SolicitacaoCriarDto();
        dto.setIdsModulos(List.of(UUID.randomUUID()));
        dto.setJustificativa("Justificativa válida com mais de vinte caracteres");
        dto.setUrgente(false);

        String resp = servico.criarSolicitacao(usuarioId, dto);

        assertTrue(resp.contains("Solicitação negada"));

        ArgumentCaptor<com.desafio.model.entidade.SolicitacaoAcesso> captor = ArgumentCaptor.forClass(com.desafio.model.entidade.SolicitacaoAcesso.class);
        verify(solicitacaoRepository, times(1)).save(captor.capture());
        assertEquals("NEGADO", captor.getValue().getStatus());
    }
}
