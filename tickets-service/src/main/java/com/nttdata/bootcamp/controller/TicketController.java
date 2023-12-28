package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
  
  private final TicketService service;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody Ticket ticket) {
    service.create(ticket);
  }

  @PutMapping("/{id}")
  public Ticket update(@PathVariable("id") Integer id, @RequestBody Ticket ticket) {
    return service.update(id, ticket);
  }

  @GetMapping("/{id}")
  public Ticket get(@PathVariable("id") Integer id) {
    return service.get(id);
  }

  @GetMapping
  public List<Ticket> getAll() {
    return service.getAll();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void save(@PathVariable("id") Integer id) {
    service.delete(id);
  }

  @GetMapping("/evento/{eventoId}")
  public List<Ticket> getTicketsByEvent(@PathVariable("eventoId") Integer eventoId) {
    return service.getTicketsByEvent(eventoId);
  }

  @DeleteMapping("/evento/{eventoId}")
  public void deleteAllByEvent(@PathVariable("eventoId") Integer eventoId) {
    service.deleteAllByEvent(eventoId);
  }

  @PatchMapping("/update-availability/{id}")
  public Boolean updateAvailability(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty) {
    return service.updateAvailability(id, qty);
  }
}
