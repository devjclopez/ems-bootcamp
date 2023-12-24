package com.nttdata.bootcamp.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryRequestDto {
  private String userId;
  private Integer ticketId;
  private String orderId;
}
