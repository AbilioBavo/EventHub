package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Participante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
   

}