CREATE TABLE produtos(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    unidade_medida VARCHAR(50) NOT NULL,
    preco_kg DECIMAL(10,2),
    preco_unidade DECIMAL(10,2),
    data_validade DATE,
    eh_pesavel BOOLEAN NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);