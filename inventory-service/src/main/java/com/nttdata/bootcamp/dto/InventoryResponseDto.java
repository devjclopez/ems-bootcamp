package com.nttdata.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponseDto {
  private Integer userId;
  private Integer ticketId;
  private String orderId;
  private InventoryStatus status;
}
