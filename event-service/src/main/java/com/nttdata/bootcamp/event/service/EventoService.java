package com.nttdata.bootcamp.event.service;

import com.nttdata.bootcamp.event.dto.EventoDto;
import com.nttdata.bootcamp.event.dto.EventoResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventoService {
  Mono<EventoResponseDto> save(EventoDto eventoDto);
  Mono<EventoResponseDto> update(Integer id, EventoDto eventoDto);
  Mono<EventoResponseDto> get(Integer id);
  Flux<EventoResponseDto> getAll();
  Mono<Void> delete(Integer id);
}
