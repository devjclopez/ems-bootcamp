package com.nttdata.bootcamp.event.service;

import com.nttdata.bootcamp.event.dto.UbicacionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UbicacionService {
  Mono<UbicacionDto> save(UbicacionDto ubicacionDto);
  Mono<UbicacionDto> update(Integer id, UbicacionDto ubicacionDto);
  Mono<UbicacionDto> get(Integer id);
  Flux<UbicacionDto> getAll();
  Mono<Void> delete(Integer id);
}
