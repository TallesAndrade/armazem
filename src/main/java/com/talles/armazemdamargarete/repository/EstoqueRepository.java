package com.talles.armazemdamargarete.repository;

import com.talles.armazemdamargarete.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProdutoId(Long produtoId);
    List<Estoque> findByAtivoTrue();
    List<Estoque> findByAtivoFalse();
    Optional<Estoque> findByIdAndAtivoTrue(Long id);
    Optional<Estoque> findByIdAndAtivoFalse(Long id);
    List<Estoque> findByProdutoNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Estoque> findByProdutoNomeContainingIgnoreCaseAndAtivoFalse(String nome);
    List<Estoque> findByProdutoNomeContainingIgnoreCase(String nome);

}
