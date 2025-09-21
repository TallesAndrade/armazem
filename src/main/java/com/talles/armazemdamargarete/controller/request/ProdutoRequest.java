package com.talles.armazemdamargarete.controller.request;

import com.talles.armazemdamargarete.enums.UnidadeMedida;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequest(

        @NotBlank(message = "O nome do produto não pode ser vazio")
        String nome,

        @NotNull(message = "A unidade de medida é obrigatória")
        UnidadeMedida unidadeMedida,

        @DecimalMin(value = "0.0", inclusive = true, message = "O preço por kg deve ser zero ou positivo")
        BigDecimal precoKg,

        @DecimalMin(value = "0.0", inclusive = true, message = "O preço por unidade deve ser zero ou positivo")
        BigDecimal precoUnidade,

        @NotNull(message = "A data de validade é obrigatória")
        @FutureOrPresent(message = "A data de validade não pode estar no passado")
        LocalDate dataValidade,

        @NotNull(message = "O campo 'ehPesavel' deve ser verdadeiro ou falso")
        boolean ehPesavel
) {}
