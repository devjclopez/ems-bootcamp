package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.dto.PaymentRequestDTO;
import com.nttdata.bootcamp.dto.PaymentResponseDTO;
import com.nttdata.bootcamp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService service;

  @PostMapping("/debit")
  public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO) {
    return service.debit(requestDTO);
  }

  @PostMapping("/credit")
  public void credit(@RequestBody PaymentRequestDTO requestDTO) {
    service.credit(requestDTO);
  }
}
