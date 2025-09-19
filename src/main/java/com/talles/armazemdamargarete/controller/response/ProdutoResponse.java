package com.talles.armazemdamargarete.controller.response;

import com.talles.armazemdamargarete.enums.UnidadeMedida;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoResponse(
        Long id,
        String nome,
        UnidadeMedida unidadeMedida,
        BigDecimal precoKg,
        BigDecimal precoUnidade,
        LocalDate dataValidade,
        boolean ehPesavel,
        boolean ativo
) {

}
