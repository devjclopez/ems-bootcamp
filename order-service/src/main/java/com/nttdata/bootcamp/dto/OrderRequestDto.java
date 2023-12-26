package com.nttdata.bootcamp.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
  private Integer userId;
  private Integer ticketId;
  private Integer eventId;
  private String orderId;
}
