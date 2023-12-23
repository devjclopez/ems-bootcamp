package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.repository.TicketRepository;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository repository;

  @Override
  public Mono<Void> create(Ticket ticket) {
    return repository.save(ticket).then();
  }

  @Override
  public Mono<Ticket> update(Integer id, Ticket ticket) {
    return null;
  }

  @Override
  public Mono<Ticket> get(Integer id) {
    return null;
  }

  @Override
  public Flux<Ticket> getAll() {
    return null;
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return null;
  }
}
