package com.talles.armazemdamargarete.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venda")
    private List<ProdutoVenda> produtosVenda = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotal;



}
