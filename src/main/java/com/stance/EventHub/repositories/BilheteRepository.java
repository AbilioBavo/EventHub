package com.stance.EventHub.repositories;

import com.stance.EventHub.models.Bilhete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, Long> {
}