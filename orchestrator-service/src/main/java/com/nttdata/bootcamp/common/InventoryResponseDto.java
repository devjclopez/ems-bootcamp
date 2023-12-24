package com.nttdata.bootcamp.common;

import lombok.Data;

@Data
public class InventoryResponseDto {
  private Integer userId;
  private Integer ticketId;
  private String orderId;
  private InventoryStatus status;
}
