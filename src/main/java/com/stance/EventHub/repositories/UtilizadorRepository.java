package com.stance.EventHub.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.stance.EventHub.models.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    UserDetails findByEmail(String username);
   
}
