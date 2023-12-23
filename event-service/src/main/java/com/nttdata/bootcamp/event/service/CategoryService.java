package com.nttdata.bootcamp.event.service;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
  Mono<CategoryDto> save(CategoryDto categoryDto);
  Mono<CategoryDto> update(Integer id, CategoryDto categoryDto);
  Mono<CategoryDto> get(Integer id);
  Flux<CategoryDto> getAll();
  Mono<Void> delete(Integer id);
  Mono<CategoryDto> getCatByTitle(String title);
}
