package com.talles.armazemdamargarete.service;

import com.talles.armazemdamargarete.controller.request.AlterarSaldoEstoqueRequest;
import com.talles.armazemdamargarete.controller.response.EstoqueResponse;
import com.talles.armazemdamargarete.entity.Estoque;
import com.talles.armazemdamargarete.entity.Produto;
import com.talles.armazemdamargarete.mapper.EstoqueMapper;
import com.talles.armazemdamargarete.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidadeKg(0.0);
        estoque.setQuantidadeUnidades(0);
        estoque.setQuantidadeMinima(0.0);
        repository.save(estoque);

    }

    public List<EstoqueResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }

    public List<EstoqueResponse> findAtivos(){
        return repository.findByAtivoTrue()
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }

    public List<EstoqueResponse> findInativos(){
        return repository.findByAtivoFalse()
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }


    public EstoqueResponse findById(Long id){
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return EstoqueMapper.toResponse(estoque);
    }

    public EstoqueResponse findByIdAtivo(Long id){
        Estoque estoque = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException());
        return EstoqueMapper.toResponse(estoque);
    }

    public EstoqueResponse findByIdInativo(Long id){
        Estoque estoque = repository.findByIdAndAtivoFalse(id)
                .orElseThrow(() -> new RuntimeException());
        return EstoqueMapper.toResponse(estoque);
    }

    public EstoqueResponse adicionarQuantidadeEstoque(Long id, AlterarSaldoEstoqueRequest request){
        adicionarSaldo(id, request);
        return findById(id);
    }

    public EstoqueResponse removerQuantidadeEstoque(Long id, AlterarSaldoEstoqueRequest request){
        removerSaldo(id, request);
        return findById(id);
    }

    public void adicionarSaldo(Long id, AlterarSaldoEstoqueRequest request){
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        if (!estoque.isAtivo()){
            throw new RuntimeException();
        }

        if (request.quantidadeKg() != null) {
            estoque.setQuantidadeKg(estoque.getQuantidadeKg() + request.quantidadeKg());
        }

        if (request.quantidadeUnidades() != null) {
            estoque.setQuantidadeUnidades(estoque.getQuantidadeUnidades() + request.quantidadeUnidades());
        }
        repository.save(estoque);
    }

    public void removerSaldo(Long id, AlterarSaldoEstoqueRequest request){
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        if (!estoque.isAtivo()){
            throw new RuntimeException();
        }
        if (request.quantidadeKg() != null) {
            if (estoque.getQuantidadeKg() < request.quantidadeKg()) {
                throw new RuntimeException("Saldo insuficiente");
            }
            estoque.setQuantidadeKg(estoque.getQuantidadeKg() - request.quantidadeKg());
        }
        if (request.quantidadeUnidades() != null) {
            if (estoque.getQuantidadeUnidades() < request.quantidadeUnidades()) {
                throw new RuntimeException("Saldo insuficiente");
            }
            estoque.setQuantidadeUnidades(estoque.getQuantidadeUnidades() - request.quantidadeUnidades());
        }
        repository.save(estoque);
    }

    private void estoqueTemSaldo(Estoque estoque){
        if (estoque.getQuantidadeKg() != 0.0 || estoque.getQuantidadeUnidades() != 0){
            throw new RuntimeException();
        }
    }

    public void desativarEstoque(Long produtoId){
        Estoque estoque = repository.findByProdutoId((produtoId))
                .orElseThrow(() -> new RuntimeException());
        if (!estoque.isAtivo()){
            throw new RuntimeException();
        }
        estoqueTemSaldo(estoque);
        estoque.setAtivo(false);
        repository.save(estoque);
    }

    public void reativarEstoque(Long produtoId){
        Estoque estoque = repository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException());
        estoque.setAtivo(true);
        repository.save(estoque);
    }

    public List<EstoqueResponse> searchByName(String nome){
        return repository.findByProdutoNomeContainingIgnoreCase(nome)
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }

    public List<EstoqueResponse> searchByNameAtivos(String nome){
        return repository.findByProdutoNomeContainingIgnoreCaseAndAtivoTrue(nome)
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }

    public List<EstoqueResponse> searchByNameInativos(String nome){
        return repository.findByProdutoNomeContainingIgnoreCaseAndAtivoFalse(nome)
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
    }
}
