package com.stance.EventHub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_bilhetes")
public class Bilhete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante; // Participante que comprou o bilhete

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento; // Evento associado ao bilhete

    @Column(nullable = false)
    private LocalDateTime criadoEm; // Data e hora de criação do bilhete

    @Column(nullable = false)
    private Double preco; 

    @Column(nullable = false)
    private Integer quantidade; 

    @Column(nullable = false)
    private Double total; 

    @Column(nullable = false)
    private boolean isUsado; 

    // Método para calcular o total
    public void calcularTotal() {
        if (preco != null && quantidade != null) {
            this.total = preco * quantidade;
        } else {
            throw new IllegalArgumentException("Preço e quantidade devem ser preenchidos para calcular o total.");
        }
    }
}