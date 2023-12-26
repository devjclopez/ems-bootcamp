package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Order;
import com.nttdata.bootcamp.model.PurchaseOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PurchaseOrderRepository extends ReactiveCrudRepository<PurchaseOrder, String> {
//  Flux<Order> findAllByUserId(String userId);
//  Flux<Order> findAllByEventoId(Integer eventoId);
}
