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

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = buscarCategoriaPorId(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaExistente.setNome(categoriaAtualizada.getNome());
        return categoriaRepository.save(categoriaExistente);
    }
    public List<Categoria> buscarTodasCategorias() {
        return categoriaRepository.findAll();
    }
}