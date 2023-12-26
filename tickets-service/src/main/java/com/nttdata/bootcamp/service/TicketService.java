package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.Ticket;

import java.util.List;

public interface TicketService {
  void create(Ticket ticket);
  Ticket update(Integer id, Ticket ticket);
  Ticket get(Integer id);
  List<Ticket> getAll();
  void delete(Integer id);
  List<Ticket> getTicketsByEvent(Integer eventoId);
  void deleteAllByEvent(Integer eventoId);

  boolean updateAvailability(Integer id, Integer qty);
}
