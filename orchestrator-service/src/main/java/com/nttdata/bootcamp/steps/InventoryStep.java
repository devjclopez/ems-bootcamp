package com.nttdata.bootcamp.steps;

import com.nttdata.bootcamp.common.InventoryRequestDto;
import com.nttdata.bootcamp.common.InventoryResponseDto;
import com.nttdata.bootcamp.common.InventoryStatus;
import com.nttdata.bootcamp.service.WorkflowStep;
import com.nttdata.bootcamp.service.WorkflowStepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class InventoryStep implements WorkflowStep {

  private final WebClient webClient;
  private final InventoryRequestDto requestDto;
  private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

  @Override
  public WorkflowStepStatus getStatus() {
    return stepStatus;
  }

  @Override
  public Mono<Boolean> process() {
    return webClient
            .post()
            .uri("/inventory/deduct")
            .body(BodyInserters.fromValue(requestDto))
            .retrieve()
            .bodyToMono(InventoryResponseDto.class)
            .map(r -> r.getStatus().equals(InventoryStatus.AVAILABLE))
            .doOnNext(b -> stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
  }

  @Override
  public Mono<Boolean> revert() {
    return webClient
            .post()
            .uri("/inventory/add")
            .body(BodyInserters.fromValue(requestDto))
            .retrieve()
            .bodyToMono(Void.class)
            .map(r -> true)
            .onErrorReturn(false);
  }
}
