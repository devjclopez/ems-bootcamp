package com.nttdata.bootcamp.common;

import lombok.Data;

@Data
public class OrchestratorResponseDTO {
  private String userId;
  private Integer ticketId;
  private String orderId;
  private Double amount;
  private OrderStatus status;
}
