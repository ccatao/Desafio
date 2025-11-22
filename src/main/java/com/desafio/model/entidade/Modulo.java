/*
 Criado por: Cleber Cisne Catão
 Projeto: Desafio
 Data de criação: 19/11/2025
*/

package com.desafio.model.entidade;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "modulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modulo {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true;

    @ElementCollection
    @CollectionTable(name = "modulo_departamentos", joinColumns = @JoinColumn(name = "modulo_id"))
    @Column(name = "departamento")
    private List<String> departamentosPermitidos;

    @ElementCollection
    @CollectionTable(name = "modulo_incompativeis", joinColumns = @JoinColumn(name = "modulo_id"))
    @Column(name = "incompativel")
    private List<String> modulosIncompativeis;
}