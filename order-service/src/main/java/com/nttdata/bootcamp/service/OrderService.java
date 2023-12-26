package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.OrchestratorResponseDto;
import com.nttdata.bootcamp.dto.OrderRequestDto;
import com.nttdata.bootcamp.dto.OrderResponseDto;
import com.nttdata.bootcamp.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
  Mono<Order> createOrder(OrderRequestDto orderRequestDto);
  Flux<OrderResponseDto> getAllOrder();

  Mono<Void> updateOrder(OrchestratorResponseDto responseDto);
}
