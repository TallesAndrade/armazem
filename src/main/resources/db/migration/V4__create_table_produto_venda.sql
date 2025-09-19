CREATE TABLE produto_venda(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    preco_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_produto_venda_venda FOREIGN KEY (venda_id) REFERENCES vendas(id),
    CONSTRAINT fk_produto_venda_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

