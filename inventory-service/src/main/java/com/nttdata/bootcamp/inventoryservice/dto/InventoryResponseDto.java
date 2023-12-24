package com.nttdata.bootcamp.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryResponseDto {
  private String userId;
  private Integer ticketId;
  private String orderId;
  private InventoryStatus status;
}
