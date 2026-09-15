package com.trokr.service;

import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Avaliacao;
import com.trokr.model.StatusAvaliacao;
import com.trokr.repository.AvaliacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Avaliação não encontrada com id: " + id
                        )
                );
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao avaliar(
            Long id,
            Integer nota,
            String descricao) {

        Avaliacao avaliacao = buscarPorId(id);

        if (avaliacao.getStatus() != StatusAvaliacao.PENDENTE) {
            throw new IllegalStateException("Avaliação já realizada");
        }

        avaliacao.setNota(nota);
        avaliacao.setDescricao(descricao);
        avaliacao.setStatus(StatusAvaliacao.AVALIADA);

        return avaliacaoRepository.save(avaliacao);
    }
}