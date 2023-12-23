package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, String> {
  Flux<Ticket> findByEventoId(Integer eventoId);
  Mono<Void> deleteAllByEventoId(Integer eventoId);
}
