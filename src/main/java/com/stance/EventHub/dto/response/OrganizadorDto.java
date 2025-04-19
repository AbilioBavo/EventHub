package com.stance.EventHub.dto.response;

import java.io.Serializable;
import com.stance.EventHub.models.Organizador;

import lombok.Data;

@Data
public class OrganizadorDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private String morada;
    private String telefone;
   
    public OrganizadorDto() {
    }

   public OrganizadorDto(Organizador organizador) {
        this.id = organizador.getId();
        this.nome = organizador.getNome();
        this.email = organizador.getEmail();
        this.morada = organizador.getMorada();
        this.telefone = organizador.getTelefone();
   }
}
