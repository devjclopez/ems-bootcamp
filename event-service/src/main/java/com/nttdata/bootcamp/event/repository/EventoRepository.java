package com.nttdata.bootcamp.event.repository;

import com.nttdata.bootcamp.event.model.Evento;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface EventoRepository extends R2dbcRepository<Evento, Integer> {

  Mono<Evento> findByTitulo(String titulo);
}
