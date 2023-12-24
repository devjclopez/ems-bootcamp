package com.nttdata.bootcamp.common;

import lombok.Data;

@Data
public class InventoryRequestDto {
  private Integer userId;
  private Integer ticketId;
  private String orderId;
}
