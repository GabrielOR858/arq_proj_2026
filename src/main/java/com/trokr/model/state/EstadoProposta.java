package com.trokr.model.state;

import com.trokr.model.Proposta;

public interface EstadoProposta {

    Status getStatus();

    void enviar(Proposta proposta);

    void homologar(Proposta proposta);

    void aprovar(Proposta proposta);

    void aceitar(Proposta proposta);

    void recusar(Proposta proposta);

    void cancelar(Proposta proposta);

    void voltar(Proposta proposta);

    void iniciarNegociacao(Proposta proposta);

    void finalizarAcordo(Proposta proposta);
}