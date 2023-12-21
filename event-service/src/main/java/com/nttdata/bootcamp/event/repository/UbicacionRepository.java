package com.nttdata.bootcamp.event.repository;

import com.nttdata.bootcamp.event.model.Ubicacion;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UbicacionRepository extends R2dbcRepository<Ubicacion, Integer> {
}
