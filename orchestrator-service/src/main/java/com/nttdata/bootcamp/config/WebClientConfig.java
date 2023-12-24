package com.nttdata.bootcamp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  @Qualifier("payment")
  public WebClient paymentClient(@Value("http://localhost:8054") String endpoint) {
    return WebClient.builder()
            .baseUrl(endpoint)
            .build();
  }

  @Bean
  @Qualifier("inventory")
  public WebClient inventoryClient(@Value("http://localhost:8053") String endpoint) {
    return WebClient.builder()
            .baseUrl(endpoint)
            .build();
  }
}
