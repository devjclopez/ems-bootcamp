package com.nttdata.bootcamp.inventoryservice.service.impl;

import com.nttdata.bootcamp.inventoryservice.dto.InventoryRequestDto;
import com.nttdata.bootcamp.inventoryservice.dto.InventoryResponseDto;
import com.nttdata.bootcamp.inventoryservice.dto.InventoryStatus;
import com.nttdata.bootcamp.inventoryservice.service.InventoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

  private Map<Integer, Integer> inventoryMap;

  @PostConstruct
  private void init() {
    inventoryMap = new HashMap<>();
    inventoryMap.put(1, 2);
    inventoryMap.put(2, 3);
    inventoryMap.put(3, 4);
  }

  // Realizar la consulta al servicio de tickets para obtener la cantidad disponible

  public InventoryResponseDto deduct(InventoryRequestDto requestDto) {
    int qty = inventoryMap.getOrDefault(requestDto.getTicketId(), 0);

    InventoryResponseDto responseDto = new InventoryResponseDto();
    responseDto.setOrderId(requestDto.getOrderId());
    responseDto.setTicketId(requestDto.getTicketId());
    responseDto.setUserId(requestDto.getUserId());
    responseDto.setStatus(InventoryStatus.UNAVAILABLE);

    System.out.println("En inventario: " + qty);

    if(qty > 0) {
      responseDto.setStatus(InventoryStatus.AVAILABLE);
      inventoryMap.put(requestDto.getTicketId(), qty - 1);
      // reducir la cantidad en el ticket
    }

    return responseDto;
  }

  public void add(InventoryRequestDto requestDto) {
    inventoryMap.computeIfPresent(requestDto.getTicketId(), (k,v) -> v + 1);
    // reponer la cantidad en el ticket
  }
}
