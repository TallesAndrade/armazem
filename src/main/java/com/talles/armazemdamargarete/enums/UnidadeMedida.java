package com.talles.armazemdamargarete.enums;

public enum UnidadeMedida {
    GRAMA("g"),
    KILO("kg"),
    UNIDADE("un");

    private final String simbolo;

    UnidadeMedida(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
