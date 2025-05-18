package com.stance.EventHub.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityFilter securityFilter) throws Exception {
        return httpSecurity.cors().and()
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sessão sem estado
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login", "/api/eventos/", "/api/eventos/{id}", "/api/eventos/pagos", "/api/eventos/gratuitos", "/api/participantes/registrar", "/api/organizadores/criar", "/api/categorias/", "/api/categorias/{id}/eventos", "/api/organizadores/").permitAll() 
                        .requestMatchers("/api/eventos/criar", "/api/eventos/delete/{id}", "/api/eventos/organizador/{id}").hasAuthority("Organizador") 
                        .requestMatchers("/api/participantes/perfil/{id}").hasAuthority("Participante") 
                        .anyRequest().authenticated() 
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro personalizado
                .build();
    }

     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}