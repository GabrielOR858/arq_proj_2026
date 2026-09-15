package com.trokr.model.state.proposta;

import com.trokr.model.Proposta;
import com.trokr.model.state.EstadoProposta;
import com.trokr.model.state.Status;

public class EstadoRascunho implements EstadoProposta {
    
    @Override
    public Status getStatus() {
    return Status.RASCUNHO;
    }

    @Override
    public void homologar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoHomologacao());
    }

    @Override
    public void cancelar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoCancelado());
    }

    @Override
    public void enviar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void aprovar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void aceitar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void recusar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void voltar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void iniciarNegociacao(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void finalizarAcordo(Proposta proposta) {
        operacaoInvalida();
    }

    private void operacaoInvalida() {
        throw new IllegalStateException(
                "Operação inválida para proposta em rascunho"
        );
    }
}