package com.stance.EventHub.dto.response;

import java.time.LocalDateTime;

import com.stance.EventHub.models.Bilhete;

import lombok.Data;

@Data
public class BilheteDto {

    private Long id;
    private String participante;
    private String evento;
    private LocalDateTime criadoEm;
    private Double preco;
    private Integer quantidade;
    private Double total;
    private boolean isUsado;

    public BilheteDto() {
    }

    public BilheteDto(Bilhete bilhete) {
        this.id = bilhete.getId();
        this.participante = bilhete.getParticipante().getNome();
        this.evento = bilhete.getEvento().getNome();
        this.criadoEm = bilhete.getCriadoEm();
        this.preco = bilhete.getPreco();
        this.quantidade = bilhete.getQuantidade();
        this.total = bilhete.getTotal();
        this.isUsado = bilhete.isUsado();
    }

    // Getters e Setters
}