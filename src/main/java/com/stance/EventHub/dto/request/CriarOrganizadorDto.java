package com.stance.EventHub.dto.request;



public record CriarOrganizadorDto(
    String nome,
    String email,
    String senha,
    String telefone,
    String morada
    ) {}
