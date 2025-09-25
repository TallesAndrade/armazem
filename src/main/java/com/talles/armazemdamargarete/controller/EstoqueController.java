package com.talles.armazemdamargarete.controller;

import com.talles.armazemdamargarete.controller.request.AlterarSaldoEstoqueRequest;
import com.talles.armazemdamargarete.controller.response.EstoqueResponse;
import com.talles.armazemdamargarete.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService estoqueService) {
        this.service = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<EstoqueResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<EstoqueResponse>> findAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<EstoqueResponse>> findInativos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findInativos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/ativos/{id}")
    public ResponseEntity<EstoqueResponse> findByIdAtivo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByIdAtivo(id));
    }

    @GetMapping("/inativos/{id}")
    public ResponseEntity<EstoqueResponse> findByIdInativo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByIdInativo(id));
    }

    @PatchMapping("/{id}/adicionar")
    public ResponseEntity<EstoqueResponse> adicionarQuantidadeEstoque(@PathVariable Long id, @Valid @RequestBody AlterarSaldoEstoqueRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.adicionarQuantidadeEstoque(id, request));
    }

    @PatchMapping("/{id}/remover")
    public ResponseEntity<EstoqueResponse> removerQuantidadeEstoque(@PathVariable Long id, @Valid @RequestBody AlterarSaldoEstoqueRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.removerQuantidadeEstoque(id, request));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<EstoqueResponse>> searchByName(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(service.searchByName(nome));
    }

    @GetMapping("/busca/ativos")
    public ResponseEntity<List<EstoqueResponse>> searchByNameAtivos(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(service.searchByNameAtivos(nome));
    }

    @GetMapping("/busca/inativos")
    public ResponseEntity<List<EstoqueResponse>> searchByNameInativos(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(service.searchByNameInativos(nome));
    }
}
