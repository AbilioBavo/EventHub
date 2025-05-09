package com.stance.EventHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stance.EventHub.dto.request.AuthenticationDto;
import com.stance.EventHub.dto.response.LoginResponseDto;
import com.stance.EventHub.models.Utilizador;
import com.stance.EventHub.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Validated AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
    
        var utilizador = (Utilizador) auth.getPrincipal();
        var token = tokenService.generateToken(utilizador);
        var userType = utilizador.getClass().getSimpleName(); // Obtém o tipo de utilizador (Organizador ou Participante)
        String nome = utilizador.getNome(); // Obtém o nome do utilizador (se necessário)
        Long id = utilizador.getId(); // Obtém o ID do utilizador (se necessário)
        
        return ResponseEntity.ok(new LoginResponseDto(token, userType, nome, id));
    }
}
