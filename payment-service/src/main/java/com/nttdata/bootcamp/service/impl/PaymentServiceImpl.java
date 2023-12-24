package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.PaymentRequestDTO;
import com.nttdata.bootcamp.dto.PaymentResponseDTO;
import com.nttdata.bootcamp.dto.PaymentStatus;
import com.nttdata.bootcamp.service.PaymentService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

  private Map<Integer, Double> paymentMap;

  @PostConstruct
  private void init() {
    paymentMap = new HashMap<>();

    paymentMap.put(1, 500d);
    paymentMap.put(2, 1000d);
    paymentMap.put(3, 700d);
  }

  // Simula el dinero del usuario userId para sumar o restar

  @Override
  public PaymentResponseDTO debit(PaymentRequestDTO requestDTO) {
    double balance = paymentMap.getOrDefault(requestDTO.getUserId(), 0d);

    PaymentResponseDTO responseDTO = new PaymentResponseDTO();
    responseDTO.setOrderId(requestDTO.getOrderId());
    responseDTO.setUserId(requestDTO.getUserId());
    responseDTO.setAmount(requestDTO.getAmount());
    responseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);

    System.out.println("Pago interno: " + balance);

    if(balance >= requestDTO.getAmount()) {
      responseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
      paymentMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
    }

    return responseDTO;
  }

  @Override
  public void credit(PaymentRequestDTO requestDTO) {
    paymentMap.computeIfPresent(requestDTO.getUserId(), (k, v) -> v + requestDTO.getAmount());
  }

  // Se podria integrar el PayIntent de Stripe (si es que alcanza el tiempo)
}
