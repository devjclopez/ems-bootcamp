package com.nttdata.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
  @Id
  private String orderId;
  private String userId;
  private Integer eventoId;
  private String tituloEvento;
  private String ticketId;
  private LocalDate fecha;
  private double monto;
  private Integer cantidad;
  private String metodoPago;
  private boolean isCompleted = false;
  private String payment_intent;
}
