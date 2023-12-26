package com.nttdata.bootcamp.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
  private String orderId;
  private Integer userId;
  private Integer ticketId;
  private Integer eventId;
  private Double amount;
  private OrderStatus status;

}
