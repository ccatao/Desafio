/*
Criado por: Cleber Cisne Catão
Projeto: Desafio
Data de criação: 19/11/2025
*/

package com.desafio.model.entidade;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Column(nullable = false)
    private String departamento;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ativo")
    private boolean ativo = true;
}