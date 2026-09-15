package com.trokr.dto;

import com.trokr.model.Proposta;
import com.trokr.model.state.Status;

public record PropostaResponseDTO(
    Long id,
    String descricao,
    Status status,
    Long propostaAnteriorId
) {

    public static PropostaResponseDTO fromEntity(Proposta proposta) {

        Long propostaAnteriorId = null;

        if (proposta.getPropostaAnterior() != null) {
            propostaAnteriorId = proposta.getPropostaAnterior().getId();
        }

        return new PropostaResponseDTO(
            proposta.getId(),
            proposta.getDescricao(),
            proposta.getStatus(),
            propostaAnteriorId
        );
    }
}