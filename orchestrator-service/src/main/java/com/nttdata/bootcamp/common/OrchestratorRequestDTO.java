package com.nttdata.bootcamp.common;

import lombok.Data;

@Data
public class OrchestratorRequestDTO {
  private Integer userId;
  private Integer ticketId;
  private String orderId;
  private Double amount;
}
