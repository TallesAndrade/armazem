package com.talles.armazemdamargarete.repository;

import com.talles.armazemdamargarete.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
