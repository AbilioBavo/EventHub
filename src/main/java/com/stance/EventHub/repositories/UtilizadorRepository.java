package com.stance.EventHub.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stance.EventHub.models.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    // Custom query methods can be defined here if needed
    Optional<Utilizador> findByEmail(String email);
}
