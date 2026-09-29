package com.trokr.repository;

import com.trokr.model.LogEvento;
import com.trokr.model.NivelLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogEventoRepository extends MongoRepository<LogEvento, String> {

    List<LogEvento> findByTipo(String tipo);

    List<LogEvento> findByNivelAndTimestampBetween(
            NivelLog nivel,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}