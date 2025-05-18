package com.stance.EventHub.controllers;

import com.stance.EventHub.dto.request.CriarOrganizadorDto;
import com.stance.EventHub.dto.response.OrganizadorDto;
import com.stance.EventHub.models.Organizador;
import com.stance.EventHub.services.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/criar")
    public ResponseEntity<OrganizadorDto> criarOrganizador(@RequestBody CriarOrganizadorDto organizadorDto) {
        Organizador organizador = new Organizador();
        organizador.setNome(organizadorDto.nome());
        organizador.setEmail(organizadorDto.email());
        organizador.setSenha(passwordEncoder.encode(organizadorDto.senha()));
        organizador.setMorada(organizadorDto.morada());
        organizador.setTelefone(organizadorDto.telefone());

        Organizador organizadorSalvo = organizadorService.salvarOrganizador(organizador);

        OrganizadorDto responseDto = new OrganizadorDto(organizadorSalvo);

        return ResponseEntity.created(
                URI.create("/api/organizadores/" + organizadorSalvo.getId())
        ).body(responseDto);
    }

    // Buscar todos os organizadores
    @GetMapping("/")
    public ResponseEntity<List<OrganizadorDto>> listarOrganizadores() {
        List<OrganizadorDto> organizadoresDto = organizadorService.listarOrganizadores()
                .stream()
                .map(OrganizadorDto::new)
                .toList();
        return ResponseEntity.ok(organizadoresDto);
    }

    // Buscar organizador por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDto> buscarOrganizadorPorId(@PathVariable Long id) {
        Organizador organizador = organizadorService.buscarOrganizadorPorId(id)
                .orElseThrow(() -> new RuntimeException("Organizador not found with id: " + id));
        OrganizadorDto organizadorDto = new OrganizadorDto(organizador);        
        return ResponseEntity.ok(organizadorDto);
    }

    // Deletar organizador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizador(@PathVariable Long id) {
        organizadorService.deletarOrganizador(id);
        return ResponseEntity.noContent().build();
    }
}