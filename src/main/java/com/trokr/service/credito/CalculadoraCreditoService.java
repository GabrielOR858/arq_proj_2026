package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculadoraCreditoService {

    private final Map<CategoriaItem, EstrategiaCredito> estrategias;

    public CalculadoraCreditoService(List<EstrategiaCredito> todas) {
        this.estrategias = todas.stream()
                .collect(Collectors.toMap(
                        EstrategiaCredito::categoria,
                        estrategia -> estrategia
                ));
    }

    public int calcular(Item item) {
        EstrategiaCredito estrategia = estrategias.get(item.getCategoria());

        if (estrategia == null) {
            throw new IllegalStateException(
                    "Sem estratégia para " + item.getCategoria()
            );
        }

        return estrategia.calcular(item);
    }
}