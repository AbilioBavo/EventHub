package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Buscar categorias por nome
    List<Categoria> findByNome(String nome);
}