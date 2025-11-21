/*
 * Criado por: Cleber Cisne Catão
 * Data de criação: 19/11/2025
 * Projeto: Desafio
 */

package com.desafio.model.entidade;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "solicitacao_item")
public class SolicitacaoItem {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @Column(nullable = false)
    private String statusItem; // ATIVO ou NEGADO

    @Column(length = 500)
    private String motivo;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
    public String getStatusItem() { return statusItem; }
    public void setStatusItem(String statusItem) { this.statusItem = statusItem; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
