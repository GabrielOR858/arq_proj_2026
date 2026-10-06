package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditoExperienciaTest {

    @Test
    void deveAtenderCategoriaExperiencia() {
        CreditoExperiencia estrategia = new CreditoExperiencia();

        assertEquals(
                CategoriaItem.EXPERIENCIA,
                estrategia.categoria()
        );
    }
}