package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

import org.springframework.stereotype.Component;

@Component
public class CreditoExperiencia implements EstrategiaCredito {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.EXPERIENCIA;
    }

    @Override
    public int calcular(Item item) {
        // TODO: definir a regra de créditos para experiências
        return 0;
    }
}