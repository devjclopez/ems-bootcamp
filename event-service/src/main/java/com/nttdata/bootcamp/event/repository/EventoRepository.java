package com.nttdata.bootcamp.event.repository;

import com.nttdata.bootcamp.event.model.Evento;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventoRepository extends R2dbcRepository<Evento, Integer> {

  @Query(value = "select * from eventos e where e.categoria_id = :categoryId;")
  Flux<Evento> findEventsByCategory(Integer categoryId);
}
