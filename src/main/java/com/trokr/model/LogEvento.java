package com.trokr.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_eventos")
public class LogEvento {

    @Id
    private String id;

    private String tipo;

    private NivelLog nivel;

    private String origem;

    private Map<String, Object> payload;

    private LocalDateTime timestamp;

    private Long usuarioId;

    public LogEvento() {
    }

    public LogEvento(String tipo, NivelLog nivel, String origem,
                     Map<String, Object> payload,
                     LocalDateTime timestamp, Long usuarioId) {
        this.tipo = tipo;
        this.nivel = nivel;
        this.origem = origem;
        this.payload = payload;
        this.timestamp = timestamp;
        this.usuarioId = usuarioId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public NivelLog getNivel() {
        return nivel;
    }

    public void setNivel(NivelLog nivel) {
        this.nivel = nivel;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}