package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Evento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Buscar eventos por nome
    List<Evento> findByNome(String nome);

    // Buscar eventos que contenham uma palavra-chave no nome
    List<Evento> findByNomeContaining(String keyword);

    // Buscar eventos por categoria
    List<Evento> findByCategoriaId(Long categoriaId);

    // Buscar eventos organizados por um organizador específico
    List<Evento> findByOrganizadorId(Long organizadorId);

    // Buscar eventos gratuitos
    List<Evento> findByIsFreeTrue();

    // Buscar eventos pagos
    List<Evento> findByIsFreeFalse();

    // Buscar eventos com bilhetes disponíveis (join com bilhetes)
    @Query("SELECT e FROM Evento e JOIN e.bilhetes b WHERE b.isUsado = false")
    List<Evento> findEventosComBilhetesDisponiveis();
}