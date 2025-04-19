package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Interesse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, Long> {
    // Buscar interesses por participante
    List<Interesse> findByParticipanteId(Long participanteId);

}