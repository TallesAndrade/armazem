package com.talles.armazemdamargarete.mapper;

import com.talles.armazemdamargarete.controller.request.ProdutoRequest;
import com.talles.armazemdamargarete.controller.response.ProdutoResponse;
import com.talles.armazemdamargarete.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequest produtoRequest){
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setUnidadeMedida(produtoRequest.unidadeMedida());
        produto.setPrecoKg(produtoRequest.precoKg());
        produto.setPrecoUnidade(produtoRequest.precoUnidade());
        produto.setDataValidade(produtoRequest.dataValidade());
        produto.setEhPesavel(produtoRequest.ehPesavel());
        return produto;
    }

    public static ProdutoResponse toResponse(Produto produto){
        return  new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getUnidadeMedida(),
                produto.getPrecoKg(),
                produto.getPrecoUnidade(),
                produto.getDataValidade(),
                produto.isEhPesavel(),
                produto.isAtivo()
        );
    }
}
