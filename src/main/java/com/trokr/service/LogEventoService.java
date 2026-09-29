package com.trokr.service;

import com.trokr.model.LogEvento;
import com.trokr.model.NivelLog;
import com.trokr.repository.LogEventoRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class LogEventoService {

    private final LogEventoRepository logEventoRepository;

    public LogEventoService(LogEventoRepository logEventoRepository) {
        this.logEventoRepository = logEventoRepository;
    }

    public void registrarInfo(
            String origem,
            String mensagem,
            Map<String, Object> payload) {

        LogEvento log = new LogEvento(
                mensagem,
                NivelLog.INFO,
                origem,
                payload,
                LocalDateTime.now(),
                null
        );

        logEventoRepository.save(log);
    }

    public void registrarErro(
            String origem,
            String mensagem,
            Map<String, Object> payload) {

        LogEvento log = new LogEvento(
                mensagem,
                NivelLog.ERROR,
                origem,
                payload,
                LocalDateTime.now(),
                null
        );

        logEventoRepository.save(log);
    }
}