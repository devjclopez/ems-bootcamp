package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.*;
import com.nttdata.bootcamp.model.Order;
import com.nttdata.bootcamp.repository.OrderRepository;
import com.nttdata.bootcamp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private WebClient webClient;

  @Autowired
  private Sinks.Many<OrchestratorRequestDto> sink;

  @Autowired
  private OrderRepository repository;

  private Double price;

  @Override
  public Mono<Order> createOrder(OrderRequestDto orderRequestDto) {
    return webClient
            .get()
            .uri("/api/v1/tickets/" + orderRequestDto.getTicketId())
            .retrieve()
            .bodyToMono(TicketDto.class)
            .doOnNext(ticketDto -> {
              this.price = ticketDto.getPrecio();
            })
            .flatMap(a -> repository.save(dtoToEntity(orderRequestDto, this.price)))
            .doOnNext(e -> orderRequestDto.setOrderId(e.getId()))
            .doOnNext(e -> emitEvent(orderRequestDto, this.price));
  }

  @Override
  public Flux<OrderResponseDto> getAllOrder() {
    return repository.findAll().map(this::entityToDto);
  }

  @Override
  public Mono<Void> updateOrder(OrchestratorResponseDto responseDto) {
    System.out.println("Response: " + responseDto.getStatus());
    return repository.findById(responseDto.getOrderId())
            .doOnNext(p -> p.setStatus(responseDto.getStatus()))
            .flatMap(repository::save)
            .then();
  }

  private void emitEvent(OrderRequestDto orderRequestDTO, Double price) {
    sink.tryEmitNext(getOrchestratorRequestDto(orderRequestDTO, price));
  }

  private Order dtoToEntity(final OrderRequestDto dto, Double price) {
    Order purchaseOrder = new Order();
    purchaseOrder.setId(dto.getOrderId());
    purchaseOrder.setTicketId(dto.getTicketId());
    purchaseOrder.setUserId(dto.getUserId());
    purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
    purchaseOrder.setPrice(price);

    purchaseOrder.setEventId(dto.getEventId());
    return purchaseOrder;
  }

  public OrchestratorRequestDto getOrchestratorRequestDto(OrderRequestDto orderRequestDTO, Double price) {
    OrchestratorRequestDto requestDTO = new OrchestratorRequestDto();
    requestDTO.setUserId(orderRequestDTO.getUserId());
    requestDTO.setAmount(price);
    requestDTO.setOrderId(orderRequestDTO.getOrderId());
    requestDTO.setTicketId(orderRequestDTO.getTicketId());
    return requestDTO;
  }

  private OrderResponseDto entityToDto(Order purchaseOrder) {
    System.out.println("Purchase Order Status::"+purchaseOrder.getStatus());
    OrderResponseDto dto = new OrderResponseDto();
    dto.setOrderId(purchaseOrder.getId());
    dto.setTicketId(purchaseOrder.getTicketId());
    dto.setUserId(purchaseOrder.getUserId());
    dto.setStatus(purchaseOrder.getStatus());
    dto.setAmount(purchaseOrder.getPrice());

    dto.setEventId(purchaseOrder.getEventId());
    return dto;
  }
}
