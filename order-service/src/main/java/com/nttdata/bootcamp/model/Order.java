package com.nttdata.bootcamp.model;

import com.nttdata.bootcamp.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {
  @Id
  private String id;
  private Integer userId;
  private Integer ticketId;
  private Integer eventId;
  private Double price;
  private OrderStatus status;
}
