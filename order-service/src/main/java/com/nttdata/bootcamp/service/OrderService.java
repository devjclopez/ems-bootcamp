package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
  Mono<Order> save(Order order);
  Mono<Void> confirmPayment(String orderId);
  Mono<Order> get(String orderId);
  Flux<Order> getAll();
  Mono<Void> delete(String orderId);

  Flux<Order> getOrdersByUserId(String userId);
  Flux<Order> getOrdersByEventId(Integer eventId);
}
