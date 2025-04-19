package com.stance.EventHub.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.stance.EventHub.models.Interesse;
import com.stance.EventHub.models.Participante;

import lombok.Data;

@Data
public class ParticipanteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private String morada;
    private String telefone;
    private String dataNascimento;
    private List<String> interesses; // Lista de nomes dos interesses

    public ParticipanteDto() {
    }

    public ParticipanteDto(Participante participante) {
        this.id = participante.getId();
        this.nome = participante.getNome();
        this.email = participante.getEmail();
        this.morada = participante.getMorada();
        this.telefone = participante.getTelefone();
        this.dataNascimento = participante.getDataNascimento();
        this.interesses = participante.getInteresses().stream()
                .map(Interesse::getNome)
                .collect(Collectors.toList());
    }
}