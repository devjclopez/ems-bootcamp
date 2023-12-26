package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  List<Ticket> findByEventoId(Integer eventoId);
  void deleteByEventoId(Integer eventoId);
}
