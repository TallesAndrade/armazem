CREATE TABLE estoques(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    produto_id BIGINT NOT NULL UNIQUE,
    quantidade_kg DOUBLE PRECISION,
    quantidade_unidades INTEGER,
    quantidade_minima DOUBLE PRECISION,
    CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

