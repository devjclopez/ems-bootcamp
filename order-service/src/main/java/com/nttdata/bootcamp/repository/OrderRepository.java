package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
//  Flux<Order> findAllByUserId(String userId);
//  Flux<Order> findAllByEventoId(Integer eventoId);
}
