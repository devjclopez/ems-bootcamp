package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.exceptions.NotFoundException;
import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.repository.TicketRepository;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
  public Mono<Ticket> update(String id, Ticket ticket) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El ticket con id " + id + " no existe")))
            .flatMap(existTicket -> {
              BeanUtils.copyProperties(ticket, existTicket);
              return Mono.just(existTicket);
            })
            .doOnNext(t ->t.setTicketId(id))
            .flatMap(repository::save);
  }

  @Override
  public Mono<Ticket> get(String id) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El ticket con id " + id + " no existe")));
  }

  @Override
  public Flux<Ticket> getAll() {
    return repository.findAll();
  }

  @Override
  public Mono<Void> delete(String id) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El ticket con id " + id + " no existe")))
            .flatMap(repository::delete);
  }
}
