package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, String> {

}
