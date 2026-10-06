package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.Usuario;
import com.trokr.repository.UsuarioRepository;
import com.trokr.service.credito.CalculadoraCreditoService;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AtribuirCreditosListener {

    private final CalculadoraCreditoService calculadora;
    private final UsuarioRepository usuarioRepository;

    public AtribuirCreditosListener(
            CalculadoraCreditoService calculadora,
            UsuarioRepository usuarioRepository) {

        this.calculadora = calculadora;
        this.usuarioRepository = usuarioRepository;
    }

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent evento) {

        Usuario usuarioA = evento.getUsuarioA();
        Usuario usuarioB = evento.getUsuarioB();

        int creditosA = calculadora.calcular(evento.getItemA());
        int creditosB = calculadora.calcular(evento.getItemB());

        usuarioA.setSaldoCreditos(
                usuarioA.getSaldoCreditos() + creditosA
        );

        usuarioB.setSaldoCreditos(
                usuarioB.getSaldoCreditos() + creditosB
        );

        usuarioRepository.save(usuarioA);
        usuarioRepository.save(usuarioB);
    }
}