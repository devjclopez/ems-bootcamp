package com.nttdata.bootcamp.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ubicacion")
public class Ubicacion {
  @Id
  private Integer id;
  private String direccion;
  private String departamento;
  private String provincia;
  private String distrito;
}
