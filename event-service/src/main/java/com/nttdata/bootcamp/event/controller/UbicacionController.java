package com.nttdata.bootcamp.event.controller;

import com.nttdata.bootcamp.event.dto.UbicacionDto;
import com.nttdata.bootcamp.event.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/ubicacion")
@RequiredArgsConstructor
public class UbicacionController {

  private final UbicacionService service;

  @PostMapping("/nuevo")
  public Mono<UbicacionDto> save(@RequestBody UbicacionDto ubicacionDto) {
    return service.save(ubicacionDto);
  }

  @PutMapping("/actualizar/{id}")
  public Mono<UbicacionDto> update(@PathVariable("id") Integer id, @RequestBody UbicacionDto ubicacionDto) {
    return service.update(id, ubicacionDto);
  }

  @GetMapping("/single/{id}")
  public Mono<UbicacionDto> get(@PathVariable("id") Integer id) {
    return service.get(id);
  }

  @GetMapping("/listar")
  public Flux<UbicacionDto> getAll() {
    return service.getAll();
  }

  @DeleteMapping("/eliminar/{id}")
  public Mono<Void> delete(@PathVariable("id") Integer id) {
    return  service.delete(id);
  }
}
