package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
}
