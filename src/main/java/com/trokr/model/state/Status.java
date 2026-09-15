package com.trokr.model.state;

public enum Status {

    // Usado pelas duas máquinas
    RASCUNHO,

    // Proposta principal
    HOMOLOGACAO,
    ATIVA,

    // Contraproposta
    EM_ANALISE,
    RECUSADO,

    // Usados pelas duas máquinas
    NEGOCIADO,
    FINALIZADO,
    CANCELADO
}