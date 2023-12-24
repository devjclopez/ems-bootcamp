package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.Order;
import com.nttdata.bootcamp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  @PostMapping
  public Mono<Order> create(@RequestBody Order order) {
    return service.save(order);
  }

  @PutMapping("/confirm/{id}")
  public Mono<Void> confirm(@PathVariable("id") String orderId) {
    return service.confirmPayment(orderId);
  }

  @GetMapping("/{id}")
  public Mono<Order> get(@PathVariable("id") String orderId) {
    return service.get(orderId);
  }

  @GetMapping
  public Flux<Order> getAll() {
    return service.getAll();
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") String orderId) {
    return service.delete(orderId);
  }

  // Busquedas
  @GetMapping("/user/{userId}")
  public Flux<Order> getOrdersByUser(@PathVariable("userId") String userId) {
    return service.getOrdersByUserId(userId);
  }

  @GetMapping("/event/{eventId}")
  public Flux<Order> getAll(@PathVariable("eventId") Integer eventId) {
    return service.getOrdersByEventId(eventId);
  }
}
