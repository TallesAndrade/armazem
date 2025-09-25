package com.talles.armazemdamargarete.controller.request;

public record AlterarSaldoEstoqueRequest(
        Double quantidadeKg,
        Integer quantidadeUnidades
) {
}
