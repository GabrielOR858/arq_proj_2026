package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.Avaliacao;
import com.trokr.model.Proposta;
import com.trokr.model.StatusAvaliacao;
import com.trokr.repository.AvaliacaoRepository;
import com.trokr.repository.PropostaRepository;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AtualizarAvaliacaoListener {

    private final AvaliacaoRepository avaliacaoRepository;
    private final PropostaRepository propostaRepository;

    public AtualizarAvaliacaoListener(
            AvaliacaoRepository avaliacaoRepository,
            PropostaRepository propostaRepository) {

        this.avaliacaoRepository = avaliacaoRepository;
        this.propostaRepository = propostaRepository;
    }

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent evento) {

        Proposta proposta = propostaRepository
                .findById(evento.getPropostaId())
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Proposta não encontrada: "
                                        + evento.getPropostaId()
                        )
                );

        Avaliacao avaliacaoA = new Avaliacao();

        avaliacaoA.setUsuario(evento.getUsuarioA());
        avaliacaoA.setUsuarioReceber(evento.getUsuarioB());
        avaliacaoA.setProposta(proposta);
        avaliacaoA.setStatus(StatusAvaliacao.PENDENTE);
        avaliacaoA.setNota(null);
        avaliacaoA.setDescricao(null);
        avaliacaoA.setData(evento.getDataConclusao());

        Avaliacao avaliacaoB = new Avaliacao();

        avaliacaoB.setUsuario(evento.getUsuarioB());
        avaliacaoB.setUsuarioReceber(evento.getUsuarioA());
        avaliacaoB.setProposta(proposta);
        avaliacaoB.setStatus(StatusAvaliacao.PENDENTE);
        avaliacaoB.setNota(null);
        avaliacaoB.setDescricao(null);
        avaliacaoB.setData(evento.getDataConclusao());

        avaliacaoRepository.save(avaliacaoA);
        avaliacaoRepository.save(avaliacaoB);
    }
}