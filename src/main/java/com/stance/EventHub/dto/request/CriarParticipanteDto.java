package com.stance.EventHub.dto.request;

import java.util.List;

public record CriarParticipanteDto(
    String nome,
    String email,
    String senha,
    String telefone,
    String dataNascimento,
    String morada,
    List<String> interesses 
    ) {}
