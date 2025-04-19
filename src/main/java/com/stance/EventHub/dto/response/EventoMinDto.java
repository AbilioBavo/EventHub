package com.stance.EventHub.dto.response;

import java.io.Serializable;

import com.stance.EventHub.models.Evento;

import lombok.Data;

@Data
public class EventoMinDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String descricaoCurta;
    private String localizacao;
    private String dataInicio;
    private String dataFim;
    private String imagemUrl;
    private boolean isFree;
    private String categoriaNome;
    private String organizadorNome;
   
    public EventoMinDto() {
    }
    
    public EventoMinDto(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricaoCurta = evento.getDescricaoCurta();
        this.localizacao = evento.getLocalizacao();
        this.dataInicio = evento.getDataInicio();
        this.dataFim = evento.getDataFim();
        this.imagemUrl = evento.getImagemUrl();
        this.isFree = evento.isFree();
        this.categoriaNome = evento.getCategoria().getNome();
        this.organizadorNome = evento.getOrganizador().getNome();
    }
    
}
