package com.trokr.repository;

import com.trokr.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Busca pelo título
    List<Item> findByTituloContainingIgnoreCase(String titulo);

    // Busca pela descrição
    List<Item> findByDescricaoContainingIgnoreCase(String descricao);

    // Busca os itens de um usuário
    List<Item> findByUsuarioProprietarioId(Long usuarioId);


}   