package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  List<Ticket> findByEventoId(Integer eventoId);
  void deleteAllByEventoId(Integer eventoId);
}
