package com.nttdata.bootcamp.event.service.impl;

import com.nttdata.bootcamp.event.dto.UbicacionDto;
import com.nttdata.bootcamp.event.exceptions.ConflictException;
import com.nttdata.bootcamp.event.exceptions.NotFoundException;
import com.nttdata.bootcamp.event.repository.CategoryRepository;
import com.nttdata.bootcamp.event.repository.UbicacionRepository;
import com.nttdata.bootcamp.event.service.CategoryService;
import com.nttdata.bootcamp.event.service.UbicacionService;
import com.nttdata.bootcamp.event.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements UbicacionService {

  private final UbicacionRepository repository;

  @Override
  public Mono<UbicacionDto> save(UbicacionDto ubicacionDto) {
    return Mono.just(ubicacionDto)
            .map(AppUtils::dtoToUbicacion)
            .flatMap(repository::save)
            .map(AppUtils::ubicacionToDto);
  }

  @Override
  public Mono<UbicacionDto> update(Integer id, UbicacionDto ubicacionDto) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("La ubicación con id: "+ id + "no existe")))
            .then(Mono.just(ubicacionDto))
            .map(AppUtils::dtoToUbicacion)
            .doOnNext(e -> e.setId(id))
            .flatMap(repository::save)
            .map(AppUtils::ubicacionToDto);
  }

  @Override
  public Mono<UbicacionDto> get(Integer id) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("La ubicación con id: "+ id + "no existe")))
            .map(AppUtils::ubicacionToDto);
  }

  @Override
  public Flux<UbicacionDto> getAll() {
    return repository.findAll().map(AppUtils::ubicacionToDto);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return repository.deleteById(id);
  }
}
