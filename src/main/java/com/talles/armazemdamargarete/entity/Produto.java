package com.talles.armazemdamargarete.entity;

import com.talles.armazemdamargarete.enums.UnidadeMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoKg;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoUnidade;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private boolean ehPesavel;


    private boolean ativo = true;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoVenda> produtoVendas = new ArrayList<>();



}
