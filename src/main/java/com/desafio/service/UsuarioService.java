/*
 * Criado por: Cleber Cisne Catão
 * Projeto: Desafio
 * Data de criação: 20/11/2025
 *
 * Serviço responsável por operações basicas de Usuario.
 */

package com.desafio.service;

import com.desafio.model.entidade.Usuario;
import com.desafio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario criar(Usuario usuario) {
        // aqui você pode adicionar regras (criptografar senha etc.)
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(UUID id, Usuario dados) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setAtivo(dados.isAtivo());
        return usuarioRepository.save(usuario);
    }

    public void remover(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
