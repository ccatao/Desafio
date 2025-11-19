// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.modelo.entidade;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solicitacao_acesso")
public class SolicitacaoAcesso {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioSolicitante;

    @Column(nullable = false)
    private String departamentoSolicitante;

    @Column(length = 500)
    private String justificativa;

    @Column(nullable = false)
    private boolean urgente;

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;

    @Column
    private LocalDateTime dataExpiracao;

    @Column(nullable = false)
    private String status; // ATIVO, NEGADO, CANCELADO

    @Column(length = 500)
    private String motivoNegacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitacao_id")
    private List<SolicitacaoItem> itens;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }
    public Usuario getUsuarioSolicitante() { return usuarioSolicitante; }
    public void setUsuarioSolicitante(Usuario usuarioSolicitante) { this.usuarioSolicitante = usuarioSolicitante; }
    public String getDepartamentoSolicitante() { return departamentoSolicitante; }
    public void setDepartamentoSolicitante(String departamentoSolicitante) { this.departamentoSolicitante = departamentoSolicitante; }
    public String getJustificativa() { return justificativa; }
    public void setJustificativa(String justificativa) { this.justificativa = justificativa; }
    public boolean isUrgente() { return urgente; }
    public void setUrgente(boolean urgente) { this.urgente = urgente; }
    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public LocalDateTime getDataExpiracao() { return dataExpiracao; }
    public void setDataExpiracao(LocalDateTime dataExpiracao) { this.dataExpiracao = dataExpiracao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMotivoNegacao() { return motivoNegacao; }
    public void setMotivoNegacao(String motivoNegacao) { this.motivoNegacao = motivoNegacao; }
    public List<SolicitacaoItem> getItens() { return itens; }
    public void setItens(List<SolicitacaoItem> itens) { this.itens = itens; }
}
