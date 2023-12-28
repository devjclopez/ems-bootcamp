package com.nttdata.bootcamp.event.service.impl;

import com.nttdata.bootcamp.event.dto.EventoDto;
import com.nttdata.bootcamp.event.dto.EventoResponseDto;
import com.nttdata.bootcamp.event.exceptions.NotFoundException;
import com.nttdata.bootcamp.event.model.Category;
import com.nttdata.bootcamp.event.model.Evento;
import com.nttdata.bootcamp.event.model.Ubicacion;
import com.nttdata.bootcamp.event.repository.EventoRepository;
import com.nttdata.bootcamp.event.repository.UbicacionRepository;
import com.nttdata.bootcamp.event.service.CategoryService;
import com.nttdata.bootcamp.event.service.EventoService;
import com.nttdata.bootcamp.event.service.UbicacionService;
import com.nttdata.bootcamp.event.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

  @Autowired
  private WebClient webClient;

  private final EventoRepository eventoRepository;
  private final UbicacionService ubicacionService;
  private final CategoryService categoryService;

  @Override
  public Mono<EventoResponseDto> save(EventoDto eventoDto) {
    return ubicacionService.save(AppUtils.ubicacionToDto(eventoDto.getUbicacion()))
            .flatMap(ubicacionDto -> {
              Evento evento = AppUtils.dtoToEvento(eventoDto);
              evento.setUbicacionId(ubicacionDto.getId());
              return Mono.just(evento);
            })
            .flatMap(eventoRepository::save)
            .flatMap(this::cargarRelaciones);
  }

  private Mono<EventoResponseDto> cargarRelaciones(Evento evento) {
    Mono<Category> categoryMono = categoryService.get(evento.getCategoriaId()).map(AppUtils::dtoToEntity);
    Mono<Ubicacion> ubicacionMono = ubicacionService.get(evento.getUbicacionId()).map(AppUtils::dtoToUbicacion);

    return Mono.zip(Mono.just(evento), categoryMono, ubicacionMono)
            .map(tupla -> {
              EventoResponseDto eventoResponse = AppUtils.eventoToResponseDto(tupla.getT1());
              eventoResponse.setCategoria(tupla.getT2());
              eventoResponse.setUbicacion(tupla.getT3());
              return eventoResponse;
            });
  }

  @Override
  public Mono<EventoResponseDto> update(Integer id, EventoDto eventoDto) {
    return eventoRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El evento con id: " + id + " no existe")))
            .flatMap(eEvent -> {
              Evento updatedEvent = AppUtils.dtoToEvento(eventoDto);
              updatedEvent.setUbicacionId(eEvent.getUbicacionId());
              if (eventoDto.getUbicacion() != null) {
                return ubicacionService.update(eEvent.getUbicacionId(),AppUtils.ubicacionToDto(eventoDto.getUbicacion()))
                        .flatMap(ubicacionDto -> {
                          updatedEvent.setUbicacionId(ubicacionDto.getId());
                          return Mono.just(updatedEvent);
                        });
              }
              return Mono.just(updatedEvent);
            })
            .doOnNext(updatedEvento -> updatedEvento.setId(id))
            .flatMap(eventoRepository::save)
            .flatMap(this::cargarRelaciones);

  }

  @Override
  public Mono<EventoResponseDto> get(Integer id) {
    return eventoRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El evento con id: "+ id + " no existe")))
            .flatMap(this::cargarRelaciones);
  }

  @Override
  public Flux<EventoResponseDto> getAll() {
    return eventoRepository.findAll()
            .flatMap(this::cargarRelaciones);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return eventoRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("El evento con id: " + id + " no existe")))
            .flatMap(evento -> ubicacionService.delete(evento.getUbicacionId())
                    .then(eventoRepository.deleteById(id)))
            .doOnSuccess(c -> {
              webClient
                      .delete()
                      .uri("/api/v1/tickets/evento/" + id)
                      .retrieve()
                      .toEntity(ResponseEntity.class)
                      .subscribe();
            })
            .then();
  }

  @Override
  public Flux<EventoResponseDto> getEventsByCategory(String category) {
    return categoryService.getCatByTitle(category)
            .switchIfEmpty(Mono.error(new NotFoundException("La categoría " + category + " no existe")))
            .flatMapMany(categoryDto -> eventoRepository.findEventsByCategory(categoryDto.getId()))
            .flatMap(this::cargarRelaciones);
  }
}
