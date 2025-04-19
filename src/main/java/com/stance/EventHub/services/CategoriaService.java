package com.stance.EventHub.services;

import com.stance.EventHub.models.Categoria;
import com.stance.EventHub.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Buscar todas as categorias
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Buscar categoria por ID
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Buscar categorias por nome exato
    public List<Categoria> buscarCategoriasPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }
}