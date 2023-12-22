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
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

  @Override
  public Mono<EventoResponseDto> save(EventoDto eventoDto) {
    return null;
  }

  private Mono<EventoResponseDto> cargarRelaciones(Evento evento) {
    return null;
  }

  @Override
  public Mono<EventoResponseDto> update(Integer id, EventoDto eventoDto) {
    return null;
  }

  @Override
  public Mono<EventoResponseDto> get(Integer id) {
    return null;
  }

  @Override
  public Flux<EventoResponseDto> getAll() {
    return null;
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return null;
  }
}
