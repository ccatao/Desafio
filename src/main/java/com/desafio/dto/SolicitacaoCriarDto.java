// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

package com.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class SolicitacaoCriarDto {

    @NotNull
    @Size(min = 1, max = 3)
    private List<UUID> idsModulos;

    @NotBlank
    @Size(min = 20, max = 500)
    private String justificativa;

    private boolean urgente;

    public List<UUID> getIdsModulos() { return idsModulos; }
    public void setIdsModulos(List<UUID> idsModulos) { this.idsModulos = idsModulos; }
    public String getJustificativa() { return justificativa; }
    public void setJustificativa(String justificativa) { this.justificativa = justificativa; }
    public boolean isUrgente() { return urgente; }
    public void setUrgente(boolean urgente) { this.urgente = urgente; }
}
