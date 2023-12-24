package com.nttdata.bootcamp.inventoryservice.controller;

import com.nttdata.bootcamp.inventoryservice.dto.InventoryRequestDto;
import com.nttdata.bootcamp.inventoryservice.dto.InventoryResponseDto;
import com.nttdata.bootcamp.inventoryservice.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryServiceImpl inventoryService;

  @PostMapping("/deduct")
  public InventoryResponseDto deduct(@RequestBody InventoryRequestDto rqRequestDTO) {
    return inventoryService.deduct(rqRequestDTO);

  }

  @PostMapping("/add")
  public void add(@RequestBody InventoryRequestDto rqRequestDTO) {
    inventoryService.add(rqRequestDTO);
  }

}
