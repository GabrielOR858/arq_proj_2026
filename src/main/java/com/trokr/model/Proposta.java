package com.trokr.model;

import com.trokr.model.state.EstadoProposta;
import com.trokr.model.state.Status;
import com.trokr.model.state.proposta.EstadoRascunho;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.PostLoad;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private Status status = Status.RASCUNHO;

    // null = proposta principal
    // preenchido = contraproposta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_anterior_id")
    private Proposta propostaAnterior;

    // O objeto State não será salvo diretamente no banco
    @Transient
    private EstadoProposta estadoAtual = new EstadoRascunho();

    public void mudarEstadoPara(EstadoProposta novoEstado) {
        this.estadoAtual = novoEstado;
        this.status = novoEstado.getStatus();
    }

    // CONTRAPROPOSTA

    public void enviar() {
        estadoAtual.enviar(this);
    }

    public void aceitar() {
        estadoAtual.aceitar(this);
    }

    // PROPOSTA PRINCIPAL

    public void homologar() {
        estadoAtual.homologar(this);
    }

    public void aprovar() {
        estadoAtual.aprovar(this);
    }

    public void voltar() {
        estadoAtual.voltar(this);
    }

    public void iniciarNegociacao() {
        estadoAtual.iniciarNegociacao(this);
    }

    // USADOS NAS DUAS

    public void recusar() {
        estadoAtual.recusar(this);
    }

    public void cancelar() {
        estadoAtual.cancelar(this);
    }

    public void finalizarAcordo() {
        estadoAtual.finalizarAcordo(this);
    }

    // IDENTIFICA CONTRAPROPOSTA

    public boolean ehContraproposta() {
        return propostaAnterior != null;
    }

    public void definirComoContraproposta(Proposta propostaAnterior) {
        this.propostaAnterior = propostaAnterior;

        mudarEstadoPara(
            new com.trokr.model.state.contraproposta.EstadoRascunho()
        );
    }

    // RECONSTRÓI O STATE APÓS CARREGAR DO BANCO

    @PostLoad
    private void restaurarEstadoAtual() {

        if (ehContraproposta()) {

            switch (status) {

                case RASCUNHO ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoRascunho();

                case EM_ANALISE ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoEmAnalise();

                case NEGOCIADO ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoNegociado();

                case RECUSADO ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoRecusado();

                case CANCELADO ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoCancelado();

                case FINALIZADO ->
                    estadoAtual =
                        new com.trokr.model.state.contraproposta.EstadoFinalizado();

                default ->
                    throw new IllegalStateException(
                        "Status inválido para contraproposta: " + status
                    );
            }

        } else {

            switch (status) {

                case RASCUNHO ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoRascunho();

                case HOMOLOGACAO ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoHomologacao();

                case ATIVA ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoAtiva();

                case NEGOCIADO ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoNegociado();

                case CANCELADO ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoCancelado();

                case FINALIZADO ->
                    estadoAtual =
                        new com.trokr.model.state.proposta.EstadoFinalizado();

                default ->
                    throw new IllegalStateException(
                        "Status inválido para proposta principal: " + status
                    );
            }
        }
    }

    // GETTERS E SETTERS

    public Usuario getUsuario() {
    return usuario;
    }

    public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
    }

    public Item getItem() {
    return item;
    }

    public void setItem(Item item) {
    this.item = item;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public Proposta getPropostaAnterior() {
        return propostaAnterior;
    }

    public void setPropostaAnterior(Proposta propostaAnterior) {
        this.propostaAnterior = propostaAnterior;
    }

    public EstadoProposta getEstadoAtual() {
        return estadoAtual;
    }
}