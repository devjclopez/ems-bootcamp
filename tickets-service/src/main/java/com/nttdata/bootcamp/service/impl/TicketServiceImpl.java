package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.exceptions.NotFoundException;
import com.nttdata.bootcamp.model.Ticket;
import com.nttdata.bootcamp.repository.TicketRepository;
import com.nttdata.bootcamp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository repository;

  @Override
  public void create(Ticket ticket) {
    repository.save(ticket);
  }

  @Override
  public Ticket update(Integer id, Ticket ticket) {
    Ticket eTicket = repository.findById(id).orElseThrow(() -> new NotFoundException("El ticket con id " + id + " no existe"));
    BeanUtils.copyProperties(ticket, eTicket);
    eTicket.setId(id);
    return repository.save(eTicket);
  }

  @Override
  public Ticket get(Integer id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("El ticket con id " + id + " no existe"));
  }

  @Override
  public List<Ticket> getAll() {
    return repository.findAll();
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public List<Ticket> getTicketsByEvent(Integer eventoId) {
    return repository.findByEventoId(eventoId);
  }

  @Override
  @Transactional
  public void deleteAllByEvent(Integer eventoId) {
    repository.deleteByEventoId(eventoId);
  }

  @Override
  public boolean updateAvailability(Integer id, Integer qty) {
    Ticket eTicket = repository.findById(id).orElseThrow(() -> new NotFoundException("El ticket con id " + id + " no existe"));
    eTicket.setDisponible(qty);
    eTicket.setId(id);
    Ticket uTicket = repository.save(eTicket);
    return uTicket.getDisponible().equals(qty);
  }
}
