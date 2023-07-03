package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.DetallePedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {
}
