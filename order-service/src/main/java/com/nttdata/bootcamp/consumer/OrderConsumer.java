package com.nttdata.bootcamp.consumer;

import com.nttdata.bootcamp.dto.OrchestratorRequestDto;
import com.nttdata.bootcamp.dto.OrchestratorResponseDto;
import com.nttdata.bootcamp.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderConsumer {

  @Autowired
  private Flux<OrchestratorRequestDto> flux;

  @Autowired
  private PurchaseOrderService update;

  @Bean
  public Supplier<Flux<OrchestratorRequestDto>> supplier() {
    return () -> flux;
  }

  @Bean
  public Consumer<Flux<OrchestratorResponseDto>> consumer() {
    return c -> c
            .doOnNext(a -> System.out.println("Consuming:: " + a))
            .flatMap(responseDto -> update.updateOrder(responseDto))
            .subscribe();
  }

}
