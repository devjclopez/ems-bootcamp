package com.nttdata.bootcamp.event.service.impl;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import com.nttdata.bootcamp.event.exceptions.ConflictException;
import com.nttdata.bootcamp.event.exceptions.NotFoundException;
import com.nttdata.bootcamp.event.model.Category;
import com.nttdata.bootcamp.event.repository.CategoryRepository;
import com.nttdata.bootcamp.event.utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Mock
  private CategoryRepository repository;

  @InjectMocks
  private CategoryServiceImpl service;

  private Category category;

  @BeforeEach
  void setUp() {
    category = new Category();
    category.setId(1);
    category.setTitle("musica");
  }

  @Test
  void saveCategory_whenCategoryDoesNotExist() {
    // Arrange
    CategoryDto categoryDto = AppUtils.entityToDto(category);
    when(repository.findByTitle(categoryDto.getTitle())).thenReturn(Mono.empty());
    when(repository.save(category)).thenReturn(Mono.just(category));
    // Act
    Mono<CategoryDto> result = service.save(categoryDto);

    // Assert
    StepVerifier.create(result)
            .expectNextMatches(dto -> dto.getTitle().equals(categoryDto.getTitle()))
            .verifyComplete();
  }

  @Test
  void saveCategory_whenCategoryExist_shouldConflictException() {
    // Arrange
    CategoryDto categoryDto = AppUtils.entityToDto(category);
    when(repository.findByTitle(anyString())).thenReturn(Mono.just(category));
    // Act
    Mono<CategoryDto> result = service.save(categoryDto);
    // Assert
    StepVerifier.create(service.save(categoryDto))
            .expectError(ConflictException.class)
            .verify();
  }

  @Test
  void update_CategoryIfExistsById() {
    // Arrange
    Integer categoryId = 1;
    CategoryDto updateCat = AppUtils.entityToDto(category);
    when(repository.findById(categoryId)).thenReturn(Mono.just(category));
    when(repository.save(any())).thenReturn(Mono.just(category));
    // Act
    Mono<CategoryDto> result = service.update(categoryId, updateCat);
    // Assert
    StepVerifier.create(result)
            .expectNext(updateCat)
            .verifyComplete();
  }

  @Test
  void update_CategoryIfNotExistsById_shouldNotFoundException() {
    // Arrange
    Integer categoryId = 1;
    CategoryDto updateCat = AppUtils.entityToDto(category);
    when(repository.findById(categoryId)).thenReturn(Mono.empty());
    // Act
    Mono<CategoryDto> result = service.update(categoryId, updateCat);
    // Assert
    StepVerifier.create(result)
            .expectError(NotFoundException.class)
            .verify();
  }

  @Test
  void get_CategoryIfExistsById() {
    // Arrange
    Integer categoryId = 1;
    CategoryDto categoryDto = AppUtils.entityToDto(category);
    when(repository.findById(categoryId)).thenReturn(Mono.just(category));
    // Act
    Mono<CategoryDto> result = service.get(categoryId);
    // Assert
    StepVerifier.create(result)
            .expectNext(categoryDto)
            .verifyComplete();
  }

  @Test
  void get_CategoryIfNotExistsById_shouldNotFoundException() {
    // Arrange
    Integer categoryId = 1;

    when(repository.findById(categoryId)).thenReturn(Mono.empty());
    // Act
    Mono<CategoryDto> result = service.get(categoryId);
    // Assert
    StepVerifier.create(result)
            .expectError(NotFoundException.class)
            .verify();
  }

  @Test
  void getAll() {
    // Arrange
    Category cat1 = category;
    Category cat2 = category;
    when(repository.findAll()).thenReturn(Flux.just(cat1, cat2));
    // Act
    Flux<CategoryDto> result = service.getAll();
    // Assert
    StepVerifier.create(result)
            .expectNextCount(2)
            .verifyComplete();
  }

  @Test
  void delete_WhenCatExists() {
    // Arrange
    Integer categoryId = 1;
    when(repository.findById(anyInt())).thenReturn(Mono.just(category));
    when(repository.delete(any())).thenReturn(Mono.empty());
    // Act
    Mono<Void> result = service.delete(categoryId);
    // Assert
    StepVerifier.create(result)
            .expectComplete()
            .verify();
  }

  @Test
  void delete_WhenCatNotExists_ShouldNotFoundException() {
    // Arrange
    Integer categoryId = 1;
    when(repository.findById(anyInt())).thenReturn(Mono.empty());
    // Act
    Mono<Void> result = service.delete(categoryId);
    // Assert
    StepVerifier.create(result)
            .expectError(NotFoundException.class)
            .verify();
  }

  @Test
  void get_CatByTitleExist() {
    // Arrange
    String title = "musica";
    CategoryDto existCat = AppUtils.entityToDto(category);
    when(repository.findByTitle(anyString())).thenReturn(Mono.just(category));
    // Act
    Mono<CategoryDto> result = service.getCatByTitle(title);
    // Asserts
    StepVerifier.create(result)
            .expectNext(existCat)
            .verifyComplete();
  }

  @Test
  void get_CatByTitleNotExist_shouldNotFoundException() {
    // Arrange
    String title = "musica";

    when(repository.findByTitle(anyString())).thenReturn(Mono.empty());
    // Act
    Mono<CategoryDto> result = service.getCatByTitle(title);
    // Asserts
    StepVerifier.create(result)
            .expectError(NotFoundException.class)
            .verify();
  }
}