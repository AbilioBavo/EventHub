package com.stance.EventHub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricaoCurta;

    @Column(nullable = false)
    private String descricaoLonga;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String dataInicio;

    @Column(nullable = false)
    private String dataFim;

    @Column(nullable = false)
    private String imagemUrl;

    private boolean isFree;
    private Double precoNormal;
    private Double precoVip;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    private Organizador organizador;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bilhete> bilhetes;

}    