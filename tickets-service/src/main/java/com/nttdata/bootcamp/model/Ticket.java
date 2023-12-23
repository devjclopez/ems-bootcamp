package com.nttdata.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
  @Id
  private String ticketId;
  private Integer eventoId;
  private String titulo;
  private String zona;
  private Integer total;
  private Integer disponible;
  private double precio;
}
