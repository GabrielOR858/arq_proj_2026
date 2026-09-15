package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.state.EstadoProposta;
import com.trokr.model.state.Status;

public class EstadoEmAnalise implements EstadoProposta {
   
    @Override
    public Status getStatus() {
    return Status.EM_ANALISE;
    }

    @Override
    public void aceitar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoNegociado());
    }

    @Override
    public void recusar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRecusado());
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
    public void homologar(Proposta proposta) {
        operacaoInvalida();
    }

    @Override
    public void aprovar(Proposta proposta) {
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
                "Operação inválida para contraproposta em análise"
        );
    }
}