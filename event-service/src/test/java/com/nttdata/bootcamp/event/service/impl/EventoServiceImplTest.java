package com.nttdata.bootcamp.event.service.impl;

import com.nttdata.bootcamp.event.dto.EventoDto;
import com.nttdata.bootcamp.event.dto.EventoResponseDto;
import com.nttdata.bootcamp.event.dto.UbicacionDto;
import com.nttdata.bootcamp.event.model.Category;
import com.nttdata.bootcamp.event.model.Evento;
import com.nttdata.bootcamp.event.model.Ubicacion;
import com.nttdata.bootcamp.event.repository.EventoRepository;
import com.nttdata.bootcamp.event.service.CategoryService;
import com.nttdata.bootcamp.event.service.UbicacionService;
import com.nttdata.bootcamp.event.service.impl.EventoServiceImpl;
import com.nttdata.bootcamp.event.service.impl.UbicacionServiceImpl;
import com.nttdata.bootcamp.event.utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceImplTest {

  @Mock
  private UbicacionService ubicacionService;
  @Mock
  private EventoRepository eventoRepository;
  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private EventoServiceImpl eventoService;

  @Test
  void saveTest() {
    // Arrange
    EventoDto eventoDto = new EventoDto();
    UbicacionDto ubicacionDto = new UbicacionDto();
    ubicacionDto.setId(1);
    Ubicacion ubicacion = AppUtils.dtoToUbicacion(ubicacionDto);
    eventoDto.setUbicacion(ubicacion);
    Evento evento = new Evento();
    evento.setUbicacionId(ubicacion.getId());
    EventoResponseDto eventoResponseDto = new EventoResponseDto();
    eventoResponseDto.setUbicacion(new Ubicacion());
    eventoResponseDto.setCategoria(new Category());

    when(ubicacionService.save(any(UbicacionDto.class))).thenReturn(Mono.just(ubicacionDto));
    when(eventoRepository.save(any(Evento.class))).thenReturn(Mono.just(evento));
    when(categoryService.get(anyInt())).thenReturn(Mono.just(AppUtils.entityToDto(new Category())));
    when(ubicacionService.get(anyInt())).thenReturn(Mono.just(AppUtils.ubicacionToDto(new Ubicacion())));

    // Act
    Mono<EventoResponseDto> result = eventoService.save(eventoDto);

    // Assert
    StepVerifier.create(result)
            .expectNext(eventoResponseDto)
            .verifyComplete();
  }

  @Test
  void update() {
  }

  @Test
  void get() {
  }

  @Test
  void getAll() {
  }

  @Test
  void delete() {
  }

  @Test
  void getEventsByCategory() {
  }
}