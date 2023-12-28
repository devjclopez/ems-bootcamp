package com.nttdata.bootcamp.config;

import com.nttdata.bootcamp.common.OrchestratorRequestDTO;
import com.nttdata.bootcamp.common.OrchestratorResponseDTO;
import com.nttdata.bootcamp.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class OrchestratorConfig {

  @Autowired
  private OrchestratorService orchestratorService;

  @Bean
  public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processor() {
    return flux -> flux.flatMap(dto -> orchestratorService.orderTicket(dto))
            .doOnNext(dto -> System.out.println("Status: " + dto.getStatus()));
  }
}
