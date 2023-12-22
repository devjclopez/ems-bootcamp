package com.nttdata.bootcamp.event.dto;

import com.nttdata.bootcamp.event.model.Category;
import com.nttdata.bootcamp.event.model.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoResponseDto {
  private Integer id;
  private String titulo;
  private String tituloCorto;
  private String descripcion;
  private String artista;
  private String tipo;
  private LocalDate fecha;
  private Integer capacidad;
  private String banner;
  private String imgP;
  private String imgS;
  private Category categoria;
  private Ubicacion ubicacion;
}
