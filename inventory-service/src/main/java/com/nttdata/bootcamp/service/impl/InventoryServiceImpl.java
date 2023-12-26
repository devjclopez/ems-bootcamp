package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.InventoryRequestDto;
import com.nttdata.bootcamp.dto.InventoryResponseDto;
import com.nttdata.bootcamp.dto.InventoryStatus;
import com.nttdata.bootcamp.dto.TicketDto;
import com.nttdata.bootcamp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryServiceImpl implements InventoryService {

  @Autowired
  private WebClient webClient;

  private Mono<TicketDto> ticket;

  public Mono<InventoryResponseDto> deduct(InventoryRequestDto requestDto) {

    ticket = webClient
            .get()
            .uri("/api/v1/tickets/" + requestDto.getTicketId())
            .retrieve()
            .bodyToMono(TicketDto.class);

    return ticket.flatMap(ticketDto -> {

      int qty = ticketDto.getDisponible();

      InventoryResponseDto responseDto = new InventoryResponseDto();
      responseDto.setOrderId(requestDto.getOrderId());
      responseDto.setTicketId(requestDto.getTicketId());
      responseDto.setUserId(requestDto.getUserId());
      responseDto.setStatus(InventoryStatus.UNAVAILABLE);

      System.out.println("En inventario: " + qty);

      if(qty > 0) {
        responseDto.setStatus(InventoryStatus.AVAILABLE);
        // reducir la cantidad en el ticket
        System.out.println("Actualizando cantidad disponible en ticket");
        webClient
                .patch()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/tickets/update-availability/" + requestDto.getTicketId())
                        .queryParam("qty", qty - 1)
                        .build()
                )
                .retrieve()
                .bodyToMono(Boolean.class)
                .subscribe();
        System.out.println("fin actualizacion");
      }
      return Mono.just(responseDto);
    });
  }

  public void add(InventoryRequestDto requestDto) {

    System.out.println("Revirtiendo...");
    // reponer la cantidad en el ticket
    ticket = webClient
            .get()
            .uri("/api/v1/tickets/" + requestDto.getTicketId())
            .retrieve()
            .bodyToMono(TicketDto.class);

    ticket.subscribe(ticketDto -> {

      int qty = ticketDto.getDisponible();

      System.out.println("En inventario: " + qty);

      System.out.println("Actualizando cantidad disponible en ticket");
      webClient
              .patch()
              .uri(uriBuilder -> uriBuilder
                      .path("/api/v1/tickets/update-availability/" + requestDto.getTicketId())
                      .queryParam("qty", qty + 1)
                      .build()
              )
              .retrieve()
              .bodyToMono(Boolean.class)
              .subscribe();
      System.out.println("fin actualizacion");
    });
  }
}
