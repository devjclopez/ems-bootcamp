package com.nttdata.bootcamp.event.repository;

import com.nttdata.bootcamp.event.model.Category;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends R2dbcRepository<Category, Integer> {

  Mono<Category> findByTitle(String title);
}
