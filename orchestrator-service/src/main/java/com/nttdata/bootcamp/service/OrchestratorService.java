package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.common.*;
import com.nttdata.bootcamp.steps.InventoryStep;
import com.nttdata.bootcamp.steps.PaymentStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrchestratorService {

  @Autowired
  @Qualifier("payment")
  private WebClient paymentClient;

  @Autowired
  @Qualifier("inventory")
  private WebClient inventoryClient;

  public Mono<OrchestratorResponseDTO> orderTicket(final OrchestratorRequestDTO requestDTO) {
    Workflow orderWorkFlow = getOrderWorkFlow(requestDTO);

    return Flux.fromStream(() -> orderWorkFlow.getSteps().stream()).flatMap(WorkflowStep::process)
            .handle(((aBoolean, synchronousSink) -> {
              if (aBoolean.booleanValue()) {
                synchronousSink.next(true);
              } else {
                synchronousSink.error(new WorkflowException("Order not processed."));
              }
            })).then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED)))
            .onErrorResume(ex -> revertOrder(orderWorkFlow, requestDTO));
  }

  private Mono<OrchestratorResponseDTO> revertOrder(final Workflow workflow, final OrchestratorRequestDTO requestDTO) {
    return Flux.fromStream(() -> workflow.getSteps().stream())
            .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
            .flatMap(WorkflowStep::revert).retry(3)
            .then(Mono.just(getResponseDTO(requestDTO, OrderStatus.ORDER_CANCELLED)));
  }

  private Workflow getOrderWorkFlow(OrchestratorRequestDTO requestDTO) {
    WorkflowStep paymentStep = new PaymentStep(paymentClient, getPaymentRequestDTO(requestDTO));
    WorkflowStep inventoryStep = new InventoryStep(inventoryClient, getInventoryRequestDTO(requestDTO));
    return new OrderWorkflow(List.of(paymentStep, inventoryStep));
  }

  private OrchestratorResponseDTO getResponseDTO(OrchestratorRequestDTO requestDTO, OrderStatus status) {
    OrchestratorResponseDTO responseDTO = new OrchestratorResponseDTO();
    responseDTO.setOrderId(requestDTO.getOrderId());
    responseDTO.setAmount(requestDTO.getAmount());
    responseDTO.setTicketId(requestDTO.getTicketId());
    responseDTO.setUserId(responseDTO.getUserId());
    responseDTO.setStatus(status);
    return responseDTO;
  }

  private PaymentRequestDTO getPaymentRequestDTO(OrchestratorRequestDTO requestDTO) {
    PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
    paymentRequestDTO.setUserId(requestDTO.getUserId());
    paymentRequestDTO.setAmount(requestDTO.getAmount());
    paymentRequestDTO.setOrderId(requestDTO.getOrderId());
    return paymentRequestDTO;
  }

  private InventoryRequestDto getInventoryRequestDTO(OrchestratorRequestDTO requestDTO) {
    InventoryRequestDto inventoryRequestDTO = new InventoryRequestDto();
    inventoryRequestDTO.setUserId(requestDTO.getUserId());
    inventoryRequestDTO.setTicketId(requestDTO.getTicketId());
    inventoryRequestDTO.setOrderId(requestDTO.getOrderId());
    return inventoryRequestDTO;
  }
}
