package com.nttdata.bootcamp.event.utils;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import com.nttdata.bootcamp.event.dto.EventoDto;
import com.nttdata.bootcamp.event.dto.EventoResponseDto;
import com.nttdata.bootcamp.event.dto.UbicacionDto;
import com.nttdata.bootcamp.event.model.Category;
import com.nttdata.bootcamp.event.model.Evento;
import com.nttdata.bootcamp.event.model.Ubicacion;
import org.springframework.beans.BeanUtils;

public class AppUtils {
  public static CategoryDto entityToDto(Category category) {
    CategoryDto categoryDto = new CategoryDto();
    BeanUtils.copyProperties(category, categoryDto);
    return categoryDto;
  }

  public static Category dtoToEntity(CategoryDto categoryDto) {
    Category category = new Category();
    categoryDto.setTitle(categoryDto.getTitle().toLowerCase());
    BeanUtils.copyProperties(categoryDto, category);
    return category;
  }

  public static UbicacionDto ubicacionToDto(Ubicacion ubicacion) {
    UbicacionDto ubicacionDto = new UbicacionDto();
    BeanUtils.copyProperties(ubicacion, ubicacionDto);
    return ubicacionDto;
  }

  public static Ubicacion dtoToUbicacion(UbicacionDto ubicacionDto) {
    Ubicacion ubicacion = new Ubicacion();
    BeanUtils.copyProperties(ubicacionDto, ubicacion);
    return ubicacion;
  }

  public static EventoDto eventoToDto(Evento evento) {
    EventoDto eventoDto = new EventoDto();
    BeanUtils.copyProperties(evento, eventoDto);
    return eventoDto;
  }

  public static Evento dtoToEvento(EventoDto eventoDto) {
    Evento evento = new Evento();
    BeanUtils.copyProperties(eventoDto, evento);
    return evento;
  }

  public static EventoResponseDto eventoToResponseDto(Evento evento) {
    EventoResponseDto eventoResponseDto = new EventoResponseDto();
    BeanUtils.copyProperties(evento, eventoResponseDto);
    return eventoResponseDto;
  }
}
