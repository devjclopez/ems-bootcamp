package com.nttdata.bootcamp.event.controller;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import com.nttdata.bootcamp.event.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService service;

  @PostMapping("/nuevo")
  public Mono<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
    return service.save(categoryDto);
  }

  @PutMapping("/actualizar/{id}")
  public Mono<CategoryDto> updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryDto categoryDto) {
    return service.update(id, categoryDto);
  }

  @GetMapping("/single/{id}")
  public Mono<CategoryDto> getCategory(@PathVariable("id") Integer id) {
    return service.get(id);
  }

  @GetMapping("/listar")
  public Flux<CategoryDto> getAllCategories() {
    return service.getAll();
  }

  @DeleteMapping("/eliminar/{id}")
  public Mono<Void> deleteCategory(@PathVariable("id") Integer id) {
    return  service.delete(id);
  }
}
