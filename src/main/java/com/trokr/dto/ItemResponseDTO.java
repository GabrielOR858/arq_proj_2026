package com.trokr.dto;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;
import java.time.LocalDateTime;

/**
 * Dados de saída de um Item.
 */
public record ItemResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Long usuarioId,
        String usuarioNome,
        LocalDateTime dataCriacao,
        CategoriaItem categoria
) {

    public static ItemResponseDTO fromEntity(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getTitulo(),
                item.getDescricao(),
                item.getUsuarioProprietario().getId(),
                item.getUsuarioProprietario().getNome(),
                item.getDataCriacao(),
                item.getCategoria()
        );
    }
}