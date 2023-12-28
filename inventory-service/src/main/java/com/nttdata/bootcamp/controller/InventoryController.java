package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.dto.InventoryRequestDto;
import com.nttdata.bootcamp.dto.InventoryResponseDto;
import com.nttdata.bootcamp.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryServiceImpl inventoryService;

  @PostMapping("/deduct")
  public Mono<InventoryResponseDto> deduct(@RequestBody InventoryRequestDto rqRequestDTO) {
    return inventoryService.deduct(rqRequestDTO);

  }

  @PostMapping("/add")
  public void add(@RequestBody InventoryRequestDto rqRequestDTO) {
    inventoryService.add(rqRequestDTO);
  }

}
