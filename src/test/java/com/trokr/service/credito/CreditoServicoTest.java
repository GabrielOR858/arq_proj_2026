package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditoServicoTest {

    @Test
    void deveAtenderCategoriaServico() {
        CreditoServico estrategia = new CreditoServico();

        assertEquals(
                CategoriaItem.SERVICO,
                estrategia.categoria()
        );
    }
}