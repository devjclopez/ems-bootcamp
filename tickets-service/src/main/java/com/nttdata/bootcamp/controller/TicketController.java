package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
  
  private final TicketService service;
  
  @PostMapping
  public Mono<Void> save(@RequestBody Ticket ticket) {
    return service.create(ticket);
  }

  @PutMapping("/{id}")
  public Mono<Ticket> update(@PathVariable("id") String id, @RequestBody Ticket ticket) {
    return service.update(id, ticket);
  }

  @GetMapping("/{id}")
  public Mono<Ticket> get(@PathVariable("id") String id) {
    return service.get(id);
  }

  @GetMapping
  public Flux<Ticket> getAll() {
    return service.getAll();
  }

  @DeleteMapping("/{id}")
  public Mono<Void> save(@PathVariable("id") String id) {
    return service.delete(id);
  }

  @GetMapping("/evento/{eventoId}")
  public Flux<Ticket> getTicketsByEvent(@PathVariable("eventoId") Integer eventoId) {
    return service.getTicketsByEvent(eventoId);
  }

  @DeleteMapping("/evento/{eventoId}")
  public Mono<Void> deleteAllByEvent(@PathVariable("eventoId") Integer eventoId) {
    return service.deleteAllByEvent(eventoId);
  }
}
