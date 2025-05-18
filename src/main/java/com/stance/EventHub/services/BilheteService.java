package com.stance.EventHub.services;

import com.stance.EventHub.models.Bilhete;
import com.stance.EventHub.repositories.BilheteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilheteService {

    @Autowired
    private BilheteRepository bilheteRepository;

    // Criar um bilhete
    public Bilhete criarBilhete(Bilhete bilhete) {

        return bilheteRepository.save(bilhete);
    }

    // Listar todos os bilhetes
    public List<Bilhete> listarBilhetes() {
        return bilheteRepository.findAll();
    }
}