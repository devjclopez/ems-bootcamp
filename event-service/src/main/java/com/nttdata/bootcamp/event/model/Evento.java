package com.nttdata.bootcamp.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "eventos")
public class Evento {
  @Id
  private Integer id;
  private String titulo;
  @Column("titulo_corto")
  private String tituloCorto;
  private String descripcion;
  private String artista;
  private String tipo;
  private LocalDate fecha;
  private Integer capacidad;
  private String banner;
  @Column("img_p")
  private String imgP;
  @Column("img_s")
  private String imgS;
  @Column("categoria_id")
  private Integer categoriaId;
  @Column("ubicacion_id")
  private Integer ubicacionId;
}























