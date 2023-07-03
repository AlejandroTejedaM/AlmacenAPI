package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
}
