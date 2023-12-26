package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.dto.OrderRequestDto;
import com.nttdata.bootcamp.dto.OrderResponseDto;
import com.nttdata.bootcamp.model.PurchaseOrder;
import com.nttdata.bootcamp.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class PurchaseOrderController {

  private final PurchaseOrderService service;

  @PostMapping("/create")
  public Mono<PurchaseOrder> createOrder(@RequestBody Mono<OrderRequestDto> orderMono) {
    return orderMono.flatMap(service::createOrder);
  }

  @GetMapping("/all")
  public Flux<OrderResponseDto> getOrders() {
    return service.getAllOrder();
  }
}
