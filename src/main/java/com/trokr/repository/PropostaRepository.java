package com.trokr.repository;

import com.trokr.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.trokr.model.state.Status;


public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByPropostaAnteriorId(Long id);

    java.util.Optional<Proposta> findByPropostaAnteriorIdAndStatus(
            Long propostaAnteriorId,
            Status status
    );
}