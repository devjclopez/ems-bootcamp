package com.nttdata.bootcamp.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UbicacionDto {
  private Integer id;
  private String direccion;
  private String departamento;
  private String provincia;
  private String distrito;
}
