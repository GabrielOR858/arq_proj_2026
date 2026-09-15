package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificarUsuariosListener {

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent evento) {

        String mensagem = String.format(
                "Troca concluída. Usuário %d trocou o item %d com o usuário %d pelo item %d.",
                evento.getUsuarioA().getId(),
                evento.getItemA().getId(),
                evento.getUsuarioB().getId(),
                evento.getItemB().getId()
        );

        System.out.println(mensagem);
    }
}