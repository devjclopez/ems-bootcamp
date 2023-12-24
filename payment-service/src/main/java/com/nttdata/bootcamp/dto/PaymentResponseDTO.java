package com.nttdata.bootcamp.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
  private Integer userId;
  private String orderId;
  private Double amount;
  private PaymentStatus status;
}
