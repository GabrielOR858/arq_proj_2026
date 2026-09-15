package com.trokr.model.state.proposta;

import com.trokr.model.Proposta;
import com.trokr.model.state.EstadoProposta;
import com.trokr.model.state.Status;

public class EstadoCancelado implements EstadoProposta {
    
    @Override
    public Status getStatus() {
    return Status.CANCELADO;
    }

    @Override
    public void enviar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void homologar(Proposta proposta) {
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
    public void cancelar(Proposta proposta) {
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
                "Proposta cancelada não pode ser alterada"
        );
    }
}