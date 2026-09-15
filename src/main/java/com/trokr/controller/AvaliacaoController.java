package com.trokr.controller;

import com.trokr.dto.AvaliarRequestDTO;
import com.trokr.dto.AvaliacaoResponseDTO;
import com.trokr.model.Avaliacao;
import com.trokr.service.AvaliacaoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PatchMapping("/{id}")
    public AvaliacaoResponseDTO avaliar(
            @PathVariable Long id,
            @Valid @RequestBody AvaliarRequestDTO dto) {

        Avaliacao avaliacao = avaliacaoService.avaliar(
                id,
                dto.nota(),
                dto.descricao()
        );

        return AvaliacaoResponseDTO.fromEntity(avaliacao);
    }
}