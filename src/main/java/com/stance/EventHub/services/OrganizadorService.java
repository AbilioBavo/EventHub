package com.stance.EventHub.services;

import com.stance.EventHub.models.Organizador;
import com.stance.EventHub.repositories.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    // Criar ou atualizar um organizador
    public Organizador salvarOrganizador(Organizador organizador) {
        if (organizador.getNome() == null || organizador.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do organizador é obrigatório.");
        }
        if (organizador.getEmail() == null || organizador.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do organizador é obrigatório.");
        }
        return organizadorRepository.save(organizador);
    }

    // Buscar todos os organizadores
    public List<Organizador> listarOrganizadores() {
        return organizadorRepository.findAll();
    }

    // Buscar organizador por ID
    public Optional<Organizador> buscarOrganizadorPorId(Long id) {
        Optional<Organizador> organizador = organizadorRepository.findById(id);
        if (organizador.isEmpty()) {
            throw new IllegalArgumentException("Organizador não encontrado com o ID: " + id);
        }
        return organizador;
    }

    // Deletar organizador por ID
    public void deletarOrganizador(Long id) {
        if (!organizadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Organizador não encontrado com o ID: " + id);
        }
        organizadorRepository.deleteById(id);
    }
}