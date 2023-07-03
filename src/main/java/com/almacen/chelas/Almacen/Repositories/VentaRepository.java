package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Integer> {
}
