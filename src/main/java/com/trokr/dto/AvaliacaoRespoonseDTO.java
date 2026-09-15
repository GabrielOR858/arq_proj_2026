package com.trokr.dto;

import com.trokr.model.Avaliacao;
import com.trokr.model.StatusAvaliacao;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
        Long id,
        Long usuarioId,
        Long usuarioReceberId,
        Integer nota,
        String descricao,
        LocalDateTime data,
        Long propostaId,
        StatusAvaliacao status
) {

    public static AvaliacaoResponseDTO fromEntity(Avaliacao avaliacao) {

        return new AvaliacaoResponseDTO(
                avaliacao.getId(),
                avaliacao.getUsuario() != null
                        ? avaliacao.getUsuario().getId()
                        : null,
                avaliacao.getUsuarioReceber() != null
                        ? avaliacao.getUsuarioReceber().getId()
                        : null,
                avaliacao.getNota(),
                avaliacao.getDescricao(),
                avaliacao.getData(),
                avaliacao.getProposta() != null
                        ? avaliacao.getProposta().getId()
                        : null,
                avaliacao.getStatus()
        );
    }
}