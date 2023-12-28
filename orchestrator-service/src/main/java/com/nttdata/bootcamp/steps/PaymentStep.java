package com.nttdata.bootcamp.steps;

import com.nttdata.bootcamp.common.PaymentRequestDTO;
import com.nttdata.bootcamp.common.PaymentResponseDTO;
import com.nttdata.bootcamp.common.PaymentStatus;
import com.nttdata.bootcamp.service.WorkflowStep;
import com.nttdata.bootcamp.service.WorkflowStepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PaymentStep implements WorkflowStep {

  private final WebClient webClient;
  private final PaymentRequestDTO requestDTO;
  private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

  @Override
  public WorkflowStepStatus getStatus() {
    return stepStatus;
  }

  @Override
  public Mono<Boolean> process() {
    return webClient
            .post()
            .uri("/payment/debit")
            .body(BodyInserters.fromValue(requestDTO))
            .retrieve()
            .bodyToMono(PaymentResponseDTO.class)
            .map(r -> r.getStatus().equals(PaymentStatus.PAYMENT_APPROVED))
            .doOnNext(b -> stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
  }

  @Override
  public Mono<Boolean> revert() {
    return webClient
            .post()
            .uri("/payment/credit")
            .body(BodyInserters.fromValue(requestDTO))
            .retrieve()
            .bodyToMono(Void.class)
            .map(r -> true)
            .onErrorReturn(false);
  }
}
