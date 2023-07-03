package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.Cajero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CajeroRepository extends CrudRepository<Cajero, Integer> {
}
