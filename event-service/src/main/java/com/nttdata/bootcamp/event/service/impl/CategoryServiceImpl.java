package com.nttdata.bootcamp.event.service.impl;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import com.nttdata.bootcamp.event.exceptions.ConflictException;
import com.nttdata.bootcamp.event.exceptions.NotFoundException;
import com.nttdata.bootcamp.event.repository.CategoryRepository;
import com.nttdata.bootcamp.event.service.CategoryService;
import com.nttdata.bootcamp.event.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  @Override
  public Mono<CategoryDto> save(CategoryDto categoryDto) {
    return repository.findByTitle(categoryDto.getTitle())
            .flatMap(c -> Mono.error( new ConflictException("La categoria ya existe")))
            .then(Mono.just(categoryDto))
            .map(AppUtils::dtoToEntity)
            .flatMap(repository::save)
            .map(AppUtils::entityToDto);
  }

  @Override
  public Mono<CategoryDto> update(Integer id, CategoryDto categoryDto) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("La categoria con id: "+ id + " no existe")))
            .then(Mono.just(categoryDto))
            .map(AppUtils::dtoToEntity)
            .doOnNext(e -> e.setId(id))
            .flatMap(repository::save)
            .map(AppUtils::entityToDto);
  }

  @Override
  public Mono<CategoryDto> get(Integer id) {
    return repository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("La categoria con id: "+ id + " no existe")))
            .map(AppUtils::entityToDto);
  }

  @Override
  public Flux<CategoryDto> getAll() {
    return repository.findAll().map(AppUtils::entityToDto);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<CategoryDto> getCatByTitle(String title) {
    return repository.findByTitle(title)
            .switchIfEmpty(Mono.error(new NotFoundException("La categoria "+ title + " no existe")))
            .map(AppUtils::entityToDto);
  }
}
