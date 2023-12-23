package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
