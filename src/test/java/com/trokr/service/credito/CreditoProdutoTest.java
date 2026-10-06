package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditoProdutoTest {

    @Test
    void deveAtenderCategoriaProduto() {
        CreditoProduto estrategia = new CreditoProduto();

        assertEquals(
                CategoriaItem.PRODUTO,
                estrategia.categoria()
        );
    }
}