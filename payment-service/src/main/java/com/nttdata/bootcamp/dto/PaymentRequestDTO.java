package com.nttdata.bootcamp.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
  private Integer userId;
  private String orderId;
  private Double amount;
}
