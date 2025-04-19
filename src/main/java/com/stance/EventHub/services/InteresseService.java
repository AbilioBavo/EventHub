package com.stance.EventHub.services;

import com.stance.EventHub.models.Interesse;
import com.stance.EventHub.models.Participante;
import com.stance.EventHub.repositories.InteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseService {

    @Autowired
    private InteresseRepository interesseRepository;

    // Salvar interesses para um participante
    public void salvarInteresses(Participante participante, List<String> nomesInteresses) {
        if (nomesInteresses != null && !nomesInteresses.isEmpty()) {
            List<Interesse> interesses = nomesInteresses.stream()
                    .map(nome -> {
                        Interesse interesse = new Interesse();
                        interesse.setNome(nome);
                        interesse.setParticipante(participante);
                        return interesse;
                    })
                    .toList();
            interesseRepository.saveAll(interesses);
        }
    }

    // Buscar interesses por participante
    public List<Interesse> listarInteressesPorParticipante(Long participanteId) {
        return interesseRepository.findByParticipanteId(participanteId);
    }
}