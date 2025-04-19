package com.stance.EventHub.dto.request;

public record CriarEventoDto(
    String nome,
    String descricaoCurta,
    String descricaoLonga,
    String localizacao,
    String dataInicio,
    String dataFim,
    String imagemUrl,
    boolean isFree,
    Double precoNormal,
    Double precoVip,
    Long categoriaId,
    Long organizadorId
) {}
