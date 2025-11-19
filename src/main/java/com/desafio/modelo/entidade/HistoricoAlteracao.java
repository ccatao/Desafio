// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.modelo.entidade;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historico_alteracao")
public class HistoricoAlteracao {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoAcesso solicitacao;

    @Column(length = 1000)
    private String descricao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public SolicitacaoAcesso getSolicitacao() { return solicitacao; }
    public void setSolicitacao(SolicitacaoAcesso solicitacao) { this.solicitacao = solicitacao; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }
}
