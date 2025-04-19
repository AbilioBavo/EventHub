package com.stance.EventHub.controllers;

import com.stance.EventHub.dto.response.EventoMinDto;
import com.stance.EventHub.models.Categoria;
import com.stance.EventHub.models.Evento;
import com.stance.EventHub.services.CategoriaService;
import com.stance.EventHub.services.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

     @Autowired
    private EventoService eventoService;

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar categorias por nome exato
    // @GetMapping("/{nome}")
    // public ResponseEntity<List<Categoria>> buscarCategoriasPorNome(@PathVariable String nome) {
    //     List<Categoria> categorias = categoriaService.buscarCategoriasPorNome(nome);
    //     return ResponseEntity.ok(categorias);
    // }

    
    // Buscar eventos por categoria ID
    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<EventoMinDto>>  buscarEventosPorCategoria(@PathVariable Long id) {
        List<Evento> eventos = eventoService.buscarEventosPorCategoria(id);
        List<EventoMinDto> responseDtos = eventos.stream()
                .map(EventoMinDto::new)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}