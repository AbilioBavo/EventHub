package com.stance.EventHub.controllers;

import com.stance.EventHub.dto.request.CriarEventoDto;
import com.stance.EventHub.dto.response.EventoDto;
import com.stance.EventHub.dto.response.EventoMinDto;
import com.stance.EventHub.models.Categoria;
import com.stance.EventHub.models.Evento;
import com.stance.EventHub.models.Organizador;
import com.stance.EventHub.services.CategoriaService;
import com.stance.EventHub.services.EventoService;
import com.stance.EventHub.services.OrganizadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CategoriaService categoriaService;

    
    @Autowired
    private OrganizadorService organizadorService;

    // Criar ou atualizar um evento
    @PostMapping("/criar")
    public ResponseEntity<EventoDto> criarEvento(@RequestBody CriarEventoDto dto) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        Organizador organizador = organizadorService.buscarOrganizadorPorId(dto.organizadorId())
                .orElseThrow(() -> new IllegalArgumentException("Organizador não encontrado"));
    
        Evento evento = new Evento();
        evento.setNome(dto.nome());
        evento.setDescricaoCurta(dto.descricaoCurta());
        evento.setDescricaoLonga(dto.descricaoLonga());
        evento.setLocalizacao(dto.localizacao());
        evento.setDataInicio(dto.dataInicio());
        evento.setDataFim(dto.dataFim());
        evento.setImagemUrl(dto.imagemUrl());
        evento.setFree(dto.isFree());
        evento.setCategoria(categoria);
        evento.setOrganizador(organizador);
    
        // Preencher preços apenas se o evento for pago
        if (!dto.isFree()) {
            if (dto.precoNormal() == null || dto.precoVip() == null) {
                throw new IllegalArgumentException("Os preços devem ser preenchidos para eventos pagos.");
            }
            evento.setPrecoNormal(dto.precoNormal());
            evento.setPrecoVip(dto.precoVip());
        }
    
        Evento eventoSalvo = eventoService.salvarEvento(evento);
    
        EventoDto responseDto = new EventoDto(eventoSalvo);
        responseDto.setCategoriaNome(categoria.getNome());
        responseDto.setOrganizadorNome(organizador.getNome());
    
        return ResponseEntity.created(
                URI.create("/eventos/" + eventoSalvo.getId())
        ).body(responseDto);
    } 

    // Buscar todos os eventos
    @GetMapping("/")
    public ResponseEntity<List<EventoMinDto>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        List<EventoMinDto> responseDtos = eventos.stream()
                .map(EventoMinDto::new)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    // Buscar evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> buscarEventoPorId(@PathVariable Long id) {
        Evento evento = eventoService.buscarEventoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
        EventoDto responseDto = new EventoDto(evento);
        return ResponseEntity.ok(responseDto);
    }

    // Deletar evento por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar eventos gratuitos
    @GetMapping("/gratuitos")
    public ResponseEntity<List<EventoMinDto>> listarEventosGratuitos() {
        List<Evento> eventosGratuitos = eventoService.listarEventosGratuitos();
        List<EventoMinDto> eventosGratuitosDto = eventosGratuitos.stream()
                .map(EventoMinDto::new)
                .toList();
        return ResponseEntity.ok(eventosGratuitosDto);
    }

    // Buscar eventos pagos
    @GetMapping("/pagos")
    public ResponseEntity<List<EventoMinDto>> listarEventosPagos() {
        List<Evento> eventosPagos = eventoService.listarEventosPagos();
        List<EventoMinDto> eventosPagosDto = eventosPagos.stream()
                .map(EventoMinDto::new)
                .toList();
        return ResponseEntity.ok(eventosPagosDto);
    }

    @GetMapping("/organizador/{id}")
    public ResponseEntity<List<EventoMinDto>> listarEventosPorOrganizador(@PathVariable Long id) {
        List<Evento> eventos = eventoService.listarEventosPorOrganizador(id);
        List<EventoMinDto> eventosPorOrganizadorDto = eventos.stream()
        .map(EventoMinDto::new)
        .toList();
        return ResponseEntity.ok(eventosPorOrganizadorDto);
    }
}