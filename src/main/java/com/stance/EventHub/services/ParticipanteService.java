package com.stance.EventHub.services;

import com.stance.EventHub.models.Interesse;
import com.stance.EventHub.models.Participante;
import com.stance.EventHub.repositories.InteresseRepository;
import com.stance.EventHub.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private InteresseRepository interesseRepository;

    @Autowired
    private InteresseService interesseService;

    // Criar participante
    public Participante salvarParticipante(Participante participante, List<String> interesses) {
        if (participante.getNome() == null || participante.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do participante é obrigatório.");
        }
        if (participante.getEmail() == null || participante.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do participante é obrigatório.");
        }

        // Salvar o participante
        Participante participanteSalvo = participanteRepository.save(participante);

        // Salvar os interesses associados
        interesseService.salvarInteresses(participanteSalvo, interesses);

        return participanteSalvo;
    }

    // Buscar todos os participantes
    public List<Participante> listarParticipantes() {
        return participanteRepository.findAll();
    }

    // Buscar participante por ID
    public Optional<Participante> buscarParticipantePorId(Long id) {
        return participanteRepository.findById(id);
    }
    // Deletar participante por ID
    public void deletarParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new IllegalArgumentException("Participante não encontrado com o ID: " + id);
        }
        participanteRepository.deleteById(id);
    }

    // Buscar interesses de um participante
    public List<Interesse> listarInteressesPorParticipante(Long participanteId) {
        return interesseRepository.findByParticipanteId(participanteId);
    }
}