package com.nttdata.bootcamp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  @Qualifier("ticket")
  public WebClient inventoryClient(@Value("http://localhost:8051") String enpoint) {
    return WebClient.builder()
            .baseUrl(enpoint)
            .build();
  }
}
