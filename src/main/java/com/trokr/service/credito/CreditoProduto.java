package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

import org.springframework.stereotype.Component;

@Component
public class CreditoProduto implements EstrategiaCredito {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.PRODUTO;
    }

    @Override
    public int calcular(Item item) {
        // TODO: definir a regra de créditos para produtos
        return 0;
    }
}