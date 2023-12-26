package com.nttdata.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {
  private Integer userId;
  private String orderId;
  private Double amount;
  private PaymentStatus status;
}
