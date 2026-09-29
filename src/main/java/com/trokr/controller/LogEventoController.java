package com.trokr.controller;

import com.trokr.adapter.RegistroDeEventos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogEventoController {

    private final RegistroDeEventos registroDeEventos;

    public LogEventoController(RegistroDeEventos registroDeEventos) {
        this.registroDeEventos = registroDeEventos;
    }

    @GetMapping
    public List<Object> listarPorTipo(@RequestParam String tipo) {
        return registroDeEventos.buscarPorTipo(tipo);
    }
}