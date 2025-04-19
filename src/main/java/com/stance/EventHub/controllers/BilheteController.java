package com.stance.EventHub.controllers;

import com.stance.EventHub.dto.request.CriarBilheteDto;
import com.stance.EventHub.dto.response.BilheteDto;
import com.stance.EventHub.models.Bilhete;
import com.stance.EventHub.models.Evento;
import com.stance.EventHub.models.Participante;
import com.stance.EventHub.services.BilheteService;
import com.stance.EventHub.services.EventoService;
import com.stance.EventHub.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bilhetes")
public class BilheteController {

    @Autowired
    private BilheteService bilheteService;

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private EventoService eventoService;

    // Criar um bilhete
    @PostMapping
    public ResponseEntity<BilheteDto> criarBilhete(@RequestBody CriarBilheteDto dto) {
            Participante participante = participanteService.buscarParticipantePorId(dto.participanteId())
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado"));

            Evento evento = eventoService.buscarEventoPorId(dto.eventoId())
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
            
            Double preco = evento.getPrecoNormal() != null ? evento.getPrecoNormal() : 0.0;    

        Bilhete bilhete = new Bilhete();
        bilhete.setParticipante(participante);
        bilhete.setEvento(evento);
        bilhete.setPreco(preco);
        bilhete.setQuantidade(dto.quantidade());
        bilhete.setCriadoEm(java.time.LocalDateTime.now());
        bilhete.calcularTotal();
        bilhete.setUsado(false);

        Bilhete bilheteSalvo = bilheteService.criarBilhete(bilhete);

        BilheteDto responseDto = new BilheteDto(bilheteSalvo);

        return ResponseEntity.created(
                URI.create("/bilhetes/" + bilheteSalvo.getId())
        ).body(responseDto);
    }

    // Listar todos os bilhetes
    @GetMapping
    public ResponseEntity<List<BilheteDto>> listarBilhetes() {
        List<BilheteDto> bilhetes = bilheteService.listarBilhetes().stream()
                .map(BilheteDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(bilhetes);
    }
}