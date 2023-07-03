package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
