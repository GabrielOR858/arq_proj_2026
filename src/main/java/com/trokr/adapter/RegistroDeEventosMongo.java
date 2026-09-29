package com.trokr.adapter;

import com.trokr.model.LogEvento;
import com.trokr.model.NivelLog;
import com.trokr.repository.LogEventoRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RegistroDeEventosMongo implements RegistroDeEventos {

    private final LogEventoRepository logEventoRepository;

    public RegistroDeEventosMongo(LogEventoRepository logEventoRepository) {
        this.logEventoRepository = logEventoRepository;
    }

    @Override
    public void registrar(String tipo, String mensagem, Object payload) {

        Map<String, Object> dados;

        if (payload instanceof Map<?, ?> mapa) {
            dados = (Map<String, Object>) mapa;
        } else {
            dados = Map.of("payload", payload);
        }

        LogEvento log = new LogEvento(
                tipo,
                NivelLog.INFO,
                mensagem,
                dados,
                LocalDateTime.now(),
                null
        );

        logEventoRepository.save(log);
    }

    @Override
    public List<Object> buscarPorTipo(String tipo) {
        return new ArrayList<>(logEventoRepository.findByTipo(tipo));
    }
}