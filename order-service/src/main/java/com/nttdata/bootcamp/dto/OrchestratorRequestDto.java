package com.nttdata.bootcamp.dto;

import lombok.Data;

@Data
public class OrchestratorRequestDto {
  private Integer userId;
  private Integer ticketId;
  private String orderId;
  private Double amount;
}
