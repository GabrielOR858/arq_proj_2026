package com.trokr.controller;

import com.trokr.model.Proposta;
import com.trokr.service.PropostaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }


    // CRUD
   

    @GetMapping
    public List<Proposta> listarTodos() {
        return propostaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Proposta buscarPorId(@PathVariable Long id) {
        return propostaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Proposta> criar(@RequestBody Proposta proposta) {

        Proposta criada = propostaService.criar(proposta);

        return ResponseEntity.ok(criada);
    }

    @PutMapping("/{id}")
    public Proposta atualizar(
            @PathVariable Long id,
            @RequestBody Proposta proposta) {

        return propostaService.atualizar(id, proposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        propostaService.remover(id);

        return ResponseEntity.noContent().build();
    }

    
    // BUSCA CONTRAPROPOSTAS


    @GetMapping("/{id}/contrapropostas")
    public List<Proposta> buscarContrapropostas(
            @PathVariable Long id) {

        return propostaService.buscarContrapropostas(id);
    }


    // TRANSIÇÕES
    

    @PatchMapping("/{id}/enviar")
    public Proposta enviar(@PathVariable Long id) {
        return propostaService.enviar(id);
    }

    @PatchMapping("/{id}/homologar")
    public Proposta homologar(@PathVariable Long id) {
        return propostaService.homologar(id);
    }

    @PatchMapping("/{id}/aprovar")
    public Proposta aprovar(@PathVariable Long id) {
        return propostaService.aprovar(id);
    }

    @PatchMapping("/{id}/aceitar")
    public Proposta aceitar(@PathVariable Long id) {
        return propostaService.aceitar(id);
    }

    @PatchMapping("/{id}/recusar")
    public Proposta recusar(@PathVariable Long id) {
        return propostaService.recusar(id);
    }

    @PatchMapping("/{id}/cancelar")
    public Proposta cancelar(@PathVariable Long id) {
        return propostaService.cancelar(id);
    }

    @PatchMapping("/{id}/voltar")
    public Proposta voltar(@PathVariable Long id) {
        return propostaService.voltar(id);
    }

    @PatchMapping("/{id}/iniciar-negociacao")
    public Proposta iniciarNegociacao(@PathVariable Long id) {
        return propostaService.iniciarNegociacao(id);
    }

    @PatchMapping("/{id}/finalizar")
    public Proposta finalizar(@PathVariable Long id) {
        return propostaService.finalizarAcordo(id);
    }
}