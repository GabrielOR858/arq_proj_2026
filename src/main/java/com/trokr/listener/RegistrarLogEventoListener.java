package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.service.LogEventoService;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrarLogEventoListener {

    private final LogEventoService logEventoService;

    public RegistrarLogEventoListener(LogEventoService logEventoService) {
        this.logEventoService = logEventoService;
    }

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent evento) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("propostaId", evento.getPropostaId());
        payload.put("usuarioA", evento.getUsuarioA().getId());
        payload.put("usuarioB", evento.getUsuarioB().getId());
        payload.put("itemA", evento.getItemA().getId());
        payload.put("itemB", evento.getItemB().getId());

        logEventoService.registrarInfo(
                "PropostaService",
                "Troca concluída",
                payload
        );
    }
}