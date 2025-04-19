package com.stance.EventHub.dto.response;

import java.io.Serializable;
import com.stance.EventHub.models.Participante;

import lombok.Data;

@Data
public class ParticipanteMinDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private String morada;
    private String telefone;
    private String dataNascimento; // Lista de nomes dos interesses

    public ParticipanteMinDto() {
    }

    public ParticipanteMinDto(Participante participante) {
        this.id = participante.getId();
        this.nome = participante.getNome();
        this.email = participante.getEmail();
        this.morada = participante.getMorada();
        this.telefone = participante.getTelefone();
        this.dataNascimento = participante.getDataNascimento();
    }
}