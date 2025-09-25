package com.talles.armazemdamargarete.controller.response;

public record EstoqueResponse(Long id,
                              Long produtoId,
                              String produtoNome,
                              Double quantidadeKg,
                              Integer quantidadeUnidades,
                              Double quantidadeMinima) {
}
