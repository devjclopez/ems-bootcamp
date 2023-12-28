package com.nttdata.bootcamp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "evento_id")
  private Integer eventoId;
  private String titulo;
  private String zona;
  private Integer total;
  private Integer disponible;
  private Double precio;
  private String descripcion;
}
