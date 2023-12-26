package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.OrchestratorResponseDto;
import com.nttdata.bootcamp.dto.OrderRequestDto;
import com.nttdata.bootcamp.dto.OrderResponseDto;
import com.nttdata.bootcamp.model.Order;
import com.nttdata.bootcamp.model.PurchaseOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseOrderService {
  Mono<PurchaseOrder> createOrder(OrderRequestDto orderRequestDto);
  Flux<OrderResponseDto> getAllOrder();

  Mono<Void> updateOrder(OrchestratorResponseDto responseDto);
}
