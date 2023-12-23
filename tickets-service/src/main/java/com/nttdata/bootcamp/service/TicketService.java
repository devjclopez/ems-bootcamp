package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {
  Mono<Void> create(Ticket ticket);
  Mono<Ticket> update(Integer id, Ticket ticket);
  Mono<Ticket> get(Integer id);
  Flux<Ticket> getAll();
  Mono<Void> delete(Integer id);
}
