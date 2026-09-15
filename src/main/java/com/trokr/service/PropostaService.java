package com.trokr.service;

import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Proposta;
import com.trokr.repository.PropostaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import com.trokr.event.TrocaConcluidaEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PropostaService(
        PropostaRepository propostaRepository,
        ApplicationEventPublisher eventPublisher) {

    this.propostaRepository = propostaRepository;
    this.eventPublisher = eventPublisher;
    }

    // CRUD

    public List<Proposta> listarTodos() {
        return propostaRepository.findAll();
    }

    public Proposta buscarPorId(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Proposta não encontrada com id: " + id
                        )
                );
    }

    public Proposta criar(Proposta proposta) {

        // Se tiver propostaAnterior, é uma contraproposta
        if (proposta.getPropostaAnterior() != null) {

            Long idAnterior = proposta.getPropostaAnterior().getId();

            Proposta propostaAnterior = buscarPorId(idAnterior);

            proposta.definirComoContraproposta(propostaAnterior);
        }

        return propostaRepository.save(proposta);
    }

    public Proposta atualizar(Long id, Proposta dados) {

        Proposta proposta = buscarPorId(id);

        proposta.setDescricao(dados.getDescricao());

        return propostaRepository.save(proposta);
    }

    public void remover(Long id) {

        Proposta proposta = buscarPorId(id);

        propostaRepository.delete(proposta);
    }

    public Proposta salvar(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    // BUSCA CONTRAPROPOSTAS

    public List<Proposta> buscarContrapropostas(Long propostaAnteriorId) {
        return propostaRepository.findByPropostaAnteriorId(propostaAnteriorId);
    }

    // TRANSIÇÕES DE ESTADO

    public Proposta enviar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.enviar();

        return propostaRepository.save(proposta);
    }

    public Proposta homologar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.homologar();

        return propostaRepository.save(proposta);
    }

    public Proposta aprovar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.aprovar();

        return propostaRepository.save(proposta);
    }

    public Proposta aceitar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.aceitar();

        return propostaRepository.save(proposta);
    }

    public Proposta recusar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.recusar();

        return propostaRepository.save(proposta);
    }

    public Proposta cancelar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.cancelar();

        return propostaRepository.save(proposta);
    }

    public Proposta voltar(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.voltar();

        return propostaRepository.save(proposta);
    }

    public Proposta iniciarNegociacao(Long id) {

        Proposta proposta = buscarPorId(id);

        proposta.iniciarNegociacao();

        return propostaRepository.save(proposta);
    }

    public Proposta finalizarAcordo(Long id) {

    Proposta proposta = buscarPorId(id);

    proposta.finalizarAcordo();

    Proposta salva = propostaRepository.save(proposta);

    if (salva.getStatus() == com.trokr.model.state.Status.FINALIZADO) {

        TrocaConcluidaEvent evento =
                construirEventoDeTrocaConcluida(salva);

        eventPublisher.publishEvent(evento);
    }

    return salva;
    }
    
    private TrocaConcluidaEvent construirEventoDeTrocaConcluida(
        Proposta proposta) {

    Proposta raiz = proposta.ehContraproposta()
            ? proposta.getPropostaAnterior()
            : proposta;

    return new TrocaConcluidaEvent(
            raiz.getId(),
            raiz.getUsuario(),
            proposta.getUsuario(),
            raiz.getItem(),
            proposta.getItem(),
            LocalDateTime.now()
    );
    }
}