package com.nttdata.bootcamp.event.controller;

import com.nttdata.bootcamp.event.dto.EventoDto;
import com.nttdata.bootcamp.event.dto.EventoResponseDto;
import com.nttdata.bootcamp.event.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

  private final EventoService service;

  @PostMapping("/nuevo")
  public Mono<EventoResponseDto> save(@RequestBody EventoDto eventoDto) {
    return service.save(eventoDto);
  }

  @PutMapping("/actualizar/{id}")
  public Mono<EventoResponseDto> update(@PathVariable("id") Integer id, @RequestBody EventoDto eventoDto) {
    return service.update(id, eventoDto);
  }

  @GetMapping("/single/{id}")
  public Mono<EventoResponseDto> get(@PathVariable("id") Integer id) {
    return service.get(id);
  }

  @GetMapping("/listar")
  public Flux<EventoResponseDto> getAll() {
    return service.getAll();
  }

  @DeleteMapping("/eliminar/{id}")
  public Mono<Void> delete(@PathVariable("id") Integer id) {
    return service.delete(id);
  }

  // Search Events
  @GetMapping("/{category}")
  public Flux<EventoResponseDto> getEventsByCat(@PathVariable("category") String category) {
    return service.getEventsByCategory(category);
  }
}
