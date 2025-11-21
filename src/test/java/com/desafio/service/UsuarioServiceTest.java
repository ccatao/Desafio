package com.desafio.service;

import com.desafio.model.entidade.Usuario;
import com.desafio.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UUID id;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
    }

    @Test
    void deveRetornarUsuarioPorId() {
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarPorId(id);
        });

        assertEquals("Usuário não encontrado", ex.getMessage());
        verify(usuarioRepository, times(1)).findById(id);
    }
}
