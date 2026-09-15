package com.trokr.dto;

public record PropostaRequestDTO(
    String descricao,
    Long propostaAnteriorId
) {
}