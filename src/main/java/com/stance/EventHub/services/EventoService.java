package com.stance.EventHub.services;

import com.stance.EventHub.models.Evento;
import com.stance.EventHub.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Criar ou atualizar um evento
    public Evento salvarEvento(Evento evento) {
        // Validação de dados obrigatórios
        if (evento.getNome() == null || evento.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do evento é obrigatório.");
        }
        if (evento.getCategoria() == null) {
            throw new IllegalArgumentException("A categoria do evento é obrigatória.");
        }
        if (!evento.isFree()) {
            if (evento.getPrecoNormal() <= 0 || evento.getPrecoVip() <= 0) {
                throw new IllegalArgumentException("Os preços para eventos pagos devem ser maiores que zero.");
            }
        }
        return eventoRepository.save(evento);
    }

    // Buscar todos os eventos
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    // Buscar evento por ID
    public Optional<Evento> buscarEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // Deletar evento por ID
    public void deletarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento não encontrado com o ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    // Buscar eventos gratuitos
    public List<Evento> listarEventosGratuitos() {
        return eventoRepository.findByIsFreeTrue();
    }

    // Buscar eventos pagos
    public List<Evento> listarEventosPagos() {
        return eventoRepository.findByIsFreeFalse();
    }

     // Buscar eventos por categoria ID
     public List<Evento> buscarEventosPorCategoria(Long categoriaId) {
        return eventoRepository.findByCategoriaId(categoriaId);
    }

    public List<Evento> listarEventosPorOrganizador(Long organizadorId) {
        return eventoRepository.findByOrganizadorId(organizadorId);
    }
}