package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.Historico;
import com.trokr.model.Proposta;
import com.trokr.model.state.Status;
import com.trokr.repository.HistoricoRepository;
import com.trokr.repository.PropostaRepository;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrarHistoricoListener {

    private final HistoricoRepository historicoRepository;
    private final PropostaRepository propostaRepository;

    public RegistrarHistoricoListener(
            HistoricoRepository historicoRepository,
            PropostaRepository propostaRepository) {

        this.historicoRepository = historicoRepository;
        this.propostaRepository = propostaRepository;
    }

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent evento) {

        Proposta proposta = propostaRepository
                .findById(evento.getPropostaId())
                .orElseThrow(() -> new IllegalStateException(
                        "Proposta não encontrada: " + evento.getPropostaId()
                ));

        Historico historico = new Historico();

        historico.setProposta(proposta);
        historico.setUsuario(evento.getUsuarioA());
        historico.setMotivo("Troca concluída");
        historico.setStatusAnterior(Status.NEGOCIADO);
        historico.setStatusNovo(Status.FINALIZADO);
        historico.setData(evento.getDataConclusao());

        historicoRepository.save(historico);
    }
}