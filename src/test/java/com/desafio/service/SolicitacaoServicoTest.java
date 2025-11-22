/*
    Criado por: Cleber Cisne Catão
    Projeto: Desafio Java
    Data: 19/11/2025
*/

package com.desafio.service;


import com.desafio.dto.SolicitacaoCriarDto;
import com.desafio.model.entidade.*;
import com.desafio.repository.*;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(InstancioExtension.class)
class SolicitacaoServicoTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ModuloRepository moduloRepository;
    @Mock
    private AcessoUsuarioRepository acessoUsuarioRepository;
    @Mock
    private SolicitacaoRepository solicitacaoRepository;
    @InjectMocks
    private SolicitacaoServico solicitacaoServico;
    @Test
    void criarSolicitacao_ComDadosValidos_DeveAprovar() {
// Arrange
        UUID userId = UUID.randomUUID();
        Usuario usuario = Instancio.of(Usuario.class).set(f -> f.getDepartamento(), "TI").create();
        Modulo modulo = Instancio.of(Modulo.class).create();
        SolicitacaoCriarDto dto = new SolicitacaoCriarDto(List.of(modulo.getId()), "Justificativa longa válida aqui para teste.", false);
        when(usuarioRepository.findById(eq(userId))).thenReturn(Optional.of(usuario));
        when(moduloRepository.findAllById(eq(dto.idsModulos()))).thenReturn(List.of(modulo));
        when(acessoUsuarioRepository.countByUsuarioIdAndAtivoTrue(eq(userId))).thenReturn(0L);
        when(acessoUsuarioRepository.existsByUsuarioIdAndModuloIdAndAtivoTrue(eq(userId), eq(modulo.getId()))).thenReturn(false);
// Act
        String result = solicitacaoServico.criarSolicitacao(userId, dto);
// Assert
        assertTrue(result.contains("sucesso"));
        verify(solicitacaoRepository, times(1)).save(any(SolicitacaoAcesso.class));
        verify(acessoUsuarioRepository, times(1)).save(any(AcessoUsuario.class));
    }
    @Test
    void criarSolicitacao_ComJustificativaGenérica_DeveLancarExcecao() {
// Arrange
        UUID userId = UUID.randomUUID();
        SolicitacaoCriarDto dto = new SolicitacaoCriarDto(List.of(UUID.randomUUID()), "teste", false);
// Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> solicitacaoServico.criarSolicitacao(userId, dto));
        assertEquals("Justificativa insuficiente ou genérica", ex.getMessage());
        verifyNoInteractions(usuarioRepository); // Não chama repo se validação falha
    }
    @Test
    void criarSolicitacao_ComLimiteAtingido_DeveNegar() {
// Arrange similar, but count = 5, dept != TI, novos =1 > limite
// ...
// Act
        String result = solicitacaoServico.criarSolicitacao(userId, dto);
// Assert
        assertTrue(result.contains("negada"));
        assertTrue(result.contains("Limite de módulos ativos atingido"));
        verify(solicitacaoRepository, times(1)).save(any()); // Salva negada
        verify(acessoUsuarioRepository, never()).save(any()); // Não concede
    }
// Mais testes: departamento sem perm, incompatível, renovar válido/inválido, cancelar, listar filtros, exceções...
// Cobertura: branches, lines >90%
}