package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Buscar categorias por nome
    List<Categoria> findByNome(String nome);

    // Buscar categorias que contenham uma palavra-chave no nome
    List<Categoria> findByNomeContaining(String keyword);

    // Buscar categorias com eventos associados
    @Query("SELECT c FROM Categoria c JOIN c.eventos e WHERE e.id IS NOT NULL")
    List<Categoria> findCategoriasComEventos();
}