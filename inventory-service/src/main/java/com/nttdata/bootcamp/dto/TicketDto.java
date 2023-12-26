package com.nttdata.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
  private String id;
  private Integer eventoId;
  private String titulo;
  private String zona;
  private Integer total;
  private Integer disponible;
  private Double precio;
  private String descripcion;
}
