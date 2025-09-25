package com.talles.armazemdamargarete.mapper;

import com.talles.armazemdamargarete.controller.response.EstoqueResponse;
import com.talles.armazemdamargarete.entity.Estoque;

public class EstoqueMapper {

    public static EstoqueResponse toResponse(Estoque estoque){
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getProduto().getNome(),
                estoque.getQuantidadeKg(),
                estoque.getQuantidadeUnidades(),
                estoque.getQuantidadeMinima()
        );
    }
}
