package com.talles.armazemdamargarete.controller;

import com.talles.armazemdamargarete.controller.request.ProdutoRequest;
import com.talles.armazemdamargarete.controller.response.ProdutoResponse;
import com.talles.armazemdamargarete.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService produtoService) {
        this.service = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProdutoResponse>> findAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ProdutoResponse>> findInativos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findInativos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findByIdAtivo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByIdAtivo(id));
    }

    @GetMapping("/inativos/{id}")
    public ResponseEntity<ProdutoResponse> findByIdInativo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByIdInativo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        service.activate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}