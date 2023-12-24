package com.nttdata.bootcamp.inventoryservice.service;

import com.nttdata.bootcamp.inventoryservice.dto.InventoryRequestDto;
import com.nttdata.bootcamp.inventoryservice.dto.InventoryResponseDto;

public interface InventoryService {
  InventoryResponseDto deduct(InventoryRequestDto requestDto);
  void add(InventoryRequestDto requestDto);
}
