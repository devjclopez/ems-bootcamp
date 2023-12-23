package com.nttdata.bootcamp.dto;

import com.nttdata.bootcamp.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MulitpleTicketDto {
  private Integer eventoId;
  List<Ticket> ticketList;
}
