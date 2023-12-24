package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.exceptions.NotFoundException;
import com.nttdata.bootcamp.model.Order;
import com.nttdata.bootcamp.repository.OrderRepository;
import com.nttdata.bootcamp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;

  @Override
  public Mono<Order> save(Order order) {
    return repository.save(order);
  }

  @Override
  public Mono<Void> confirmPayment(String orderId) {
    //TODO: reducir la cantidad de tickets disponibles en el tickets service
    return repository.findById(orderId)
            .switchIfEmpty(Mono.error(new NotFoundException("La orden con id " + orderId + " no existe")))
            .doOnNext(existOrder -> existOrder.setCompleted(true))
            .flatMap(repository::save).then();
  }

  @Override
  public Mono<Order> get(String orderId) {
    return repository.findById(orderId)
            .switchIfEmpty(Mono.error(new NotFoundException("La orden con id " + orderId + " no existe")));
  }

  @Override
  public Flux<Order> getAll() {
    return repository.findAll();
  }

  @Override
  public Mono<Void> delete(String orderId) {
    return repository.findById(orderId)
            .switchIfEmpty(Mono.error(new NotFoundException("La orden con id " + orderId + " no existe")))
            .flatMap(repository::delete);
  }

  @Override
  public Flux<Order> getOrdersByUserId(String userId) {
    return repository.findAllByUserId(userId)
            .switchIfEmpty(Mono.error(new NotFoundException("Las ordenes para el usuario " + userId + " no existen")));
  }

  @Override
  public Flux<Order> getOrdersByEventId(Integer eventId) {
    return repository.findAllByEventoId(eventId)
            .switchIfEmpty(Mono.error(new NotFoundException("Las ordenes para el evento " + eventId + " no existen")));
  }
}
