package com.stance.EventHub.controllers;

import com.stance.EventHub.dto.request.CriarParticipanteDto;
import com.stance.EventHub.dto.response.ParticipanteDto;
import com.stance.EventHub.dto.response.ParticipanteMinDto;
import com.stance.EventHub.models.Participante;
import com.stance.EventHub.services.ParticipanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<ParticipanteDto> criarParticipante(@RequestBody CriarParticipanteDto participanteDto) {
        Participante participante = new Participante();
        participante.setNome(participanteDto.nome());
        participante.setEmail(participanteDto.email());
        participante.setSenha(participanteDto.senha());
        participante.setMorada(participanteDto.morada());
        participante.setTelefone(participanteDto.telefone());
        participante.setDataNascimento(participanteDto.dataNascimento());
    
        // Salvar o participante com os interesses
        Participante participanteSalvo = participanteService.salvarParticipante(participante, participanteDto.interesses());
    
        // Criar o DTO de resposta
        ParticipanteDto responseDto = new ParticipanteDto(participanteSalvo);
    
        return ResponseEntity.created(
                URI.create("/api/participantes/" + participanteSalvo.getId())
        ).body(responseDto);
    }

    // Buscar todos os participantees
    @GetMapping
    public ResponseEntity<List<ParticipanteMinDto>> listarParticipantes() {
        List<ParticipanteMinDto> participantesMinDto = participanteService.listarParticipantes()
                .stream()
                .map(ParticipanteMinDto::new) // Mapeia para o DTO simplificado
                .toList();
        return ResponseEntity.ok(participantesMinDto);
    }

    // Buscar participante por ID
    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDto> buscarparticipantePorId(@PathVariable Long id) {
        Participante participante = participanteService.buscarParticipantePorId(id)
                .orElseThrow(() -> new RuntimeException("Participante not found with id: " + id));
        ParticipanteDto participanteDto = new ParticipanteDto(participante);        
        return ResponseEntity.ok(participanteDto);
    }

    // Deletar organizador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticcipante(@PathVariable Long id) {
        participanteService.deletarParticipante(id);
        return ResponseEntity.noContent().build();
    }
}