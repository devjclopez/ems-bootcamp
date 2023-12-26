package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.*;
import com.nttdata.bootcamp.model.PurchaseOrder;
import com.nttdata.bootcamp.repository.PurchaseOrderRepository;
import com.nttdata.bootcamp.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

  @Autowired
  private WebClient webClient;

  @Autowired
  private Sinks.Many<OrchestratorRequestDto> sink;

  @Autowired
  private PurchaseOrderRepository repository;

  private Double price;

  @Override
  public Mono<PurchaseOrder> createOrder(OrderRequestDto orderRequestDto) {
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

  private PurchaseOrder dtoToEntity(final OrderRequestDto dto, Double price) {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
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

  private OrderResponseDto entityToDto(PurchaseOrder purchaseOrder) {
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
