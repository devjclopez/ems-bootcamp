package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.InventoryRequestDto;
import com.nttdata.bootcamp.dto.InventoryResponseDto;
import reactor.core.publisher.Mono;

public interface InventoryService {
  Mono<InventoryResponseDto> deduct(InventoryRequestDto requestDto);
  void add(InventoryRequestDto requestDto);
}
