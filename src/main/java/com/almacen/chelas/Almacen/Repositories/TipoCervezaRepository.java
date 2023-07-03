package com.almacen.chelas.Almacen.Repositories;

import com.almacen.chelas.Almacen.Models.TipoCerveza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface TipoCervezaRepository extends CrudRepository<TipoCerveza, Integer> {
}
