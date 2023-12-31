package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.Cajero;
import com.almacen.chelas.Almacen.Models.TipoCerveza;
import com.almacen.chelas.Almacen.Models.Venta;
import com.almacen.chelas.Almacen.Repositories.CajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")

@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    CajeroRepository cajeroRepository;

    @GetMapping
    public ResponseEntity<Iterable<Cajero>> findAll() {
        return ResponseEntity.ok(cajeroRepository.findAll());
    }


    @GetMapping("/{cajeroId}")
    public ResponseEntity<Cajero> findById(@PathVariable Integer cajeroId) {
        Optional<Cajero> cajeroOptional = cajeroRepository.findById(cajeroId);
        if (cajeroOptional.isPresent()) {
            return ResponseEntity.ok(cajeroOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cajero cajero, UriComponentsBuilder ucb) {
        Cajero savedCajero = cajeroRepository.save(cajero);
        URI uri = ucb.path("cajero/{cajeroId}").buildAndExpand(savedCajero.getCajeroId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{cajeroId}")
    public ResponseEntity<Void> update(@PathVariable Integer cajeroId, @RequestBody Cajero cajeroAct) {
        Optional<Cajero> cajeroAnterior = cajeroRepository.findById(cajeroId);
        if (cajeroAnterior != null) {
            cajeroAct.setCajeroId(cajeroAnterior.get().getCajeroId());
            cajeroRepository.save(cajeroAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cajeroId}")
    public ResponseEntity<Void> delete(@PathVariable Integer cajeroId) {
        if (cajeroRepository.findById(cajeroId).get() == null) {
            return ResponseEntity.notFound().build();
        }
        cajeroRepository.deleteById(cajeroId);
        return ResponseEntity.noContent().build();
    }
}

