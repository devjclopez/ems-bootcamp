package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {
  Mono<Void> create(Ticket ticket);
  Mono<Ticket> update(String id, Ticket ticket);
  Mono<Ticket> get(String id);
  Flux<Ticket> getAll();
  Mono<Void> delete(String id);
  Flux<Ticket> getTicketsByEvent(Integer eventoId);
  Mono<Void> deleteAllByEvent(Integer eventoId);
}
