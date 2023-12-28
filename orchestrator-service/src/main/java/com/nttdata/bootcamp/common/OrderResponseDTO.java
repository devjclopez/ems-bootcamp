package com.nttdata.bootcamp.common;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDTO {
    private String orderId;
    private Integer userId;
    private Integer ticketId;
    private Double amount;
    private OrderStatus status;
}
