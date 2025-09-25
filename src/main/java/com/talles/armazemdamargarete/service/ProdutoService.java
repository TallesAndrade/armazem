package com.talles.armazemdamargarete.service;

import com.talles.armazemdamargarete.controller.request.ProdutoRequest;
import com.talles.armazemdamargarete.controller.response.ProdutoResponse;
import com.talles.armazemdamargarete.entity.Produto;
import com.talles.armazemdamargarete.mapper.ProdutoMapper;
import com.talles.armazemdamargarete.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    private final EstoqueService estoqueService;

    public ProdutoService(ProdutoRepository repository,EstoqueService estoqueService) {
        this.repository = repository;
        this.estoqueService = estoqueService;
    }

    public ProdutoResponse save(ProdutoRequest request) {
        Produto produto = ProdutoMapper.toEntity(request);
        Produto produtoSave = repository.save(produto);
        estoqueService.criarEstoque(produtoSave);
        return ProdutoMapper.toResponse(produtoSave);
    }

    public List<ProdutoResponse> findAll() {
        return repository.findAll().stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    public List<ProdutoResponse> findAtivos() {
        return repository.findByAtivoTrue().stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    public List<ProdutoResponse> findInativos() {
        return repository.findByAtivoFalse().stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    public ProdutoResponse findByIdAtivo(Long id) {
        Produto produto = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Produto ativo não encontrado"));
        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse findByIdInativo(Long id) {
        Produto produto = repository.findByIdAndAtivoFalse(id)
                .orElseThrow(() -> new RuntimeException("Produto inativo não encontrado"));
        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(request.nome());
        produto.setUnidadeMedida(request.unidadeMedida());
        produto.setPrecoKg(request.precoKg());
        produto.setPrecoUnidade(request.precoUnidade());
        produto.setDataValidade(request.dataValidade());
        produto.setEhPesavel(request.ehPesavel());

        return ProdutoMapper.toResponse(repository.save(produto));
    }

    public void softDelete(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow();

        if (!produto.isAtivo()) {
            throw new RuntimeException();
        }

        estoqueService.desativarEstoque(id);
        produto.setAtivo(false);
        repository.save(produto);
    }

    public void activate(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow();

        if (produto.isAtivo()) {
            throw new RuntimeException();
        }

        produto.setAtivo(true);
        estoqueService.reativarEstoque(id);
        repository.save(produto);
    }

    public List<ProdutoResponse> searchByName(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    public List<ProdutoResponse> searchByNameInativos(String nome) {
        return repository.findByNomeContainingIgnoreCaseAndAtivoFalse(nome).stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    public List<ProdutoResponse> searchByNameAtivos(String nome) {
        return repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome).stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }
}