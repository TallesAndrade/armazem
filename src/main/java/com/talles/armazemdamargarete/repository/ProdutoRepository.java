package com.talles.armazemdamargarete.repository;

import com.talles.armazemdamargarete.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByAtivoTrue();
    List<Produto> findByAtivoFalse();
    Optional<Produto> findByIdAndAtivoTrue(Long id);
    Optional<Produto> findByIdAndAtivoFalse(Long id);
    List<Produto> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Produto> findByNomeContainingIgnoreCaseAndAtivoFalse(String nome);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
