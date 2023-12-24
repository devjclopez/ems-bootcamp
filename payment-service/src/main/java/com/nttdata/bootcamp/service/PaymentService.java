package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.PaymentRequestDTO;
import com.nttdata.bootcamp.dto.PaymentResponseDTO;

public interface PaymentService {

  PaymentResponseDTO debit(PaymentRequestDTO requestDTO);
  void credit(PaymentRequestDTO requestDTO);
}
