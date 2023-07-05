package com.almacen.chelas.Almacen.Controllers;


import com.almacen.chelas.Almacen.Models.Cajero;
import com.almacen.chelas.Almacen.Models.Producto;
import com.almacen.chelas.Almacen.Models.TipoCerveza;
import com.almacen.chelas.Almacen.Models.Venta;
import com.almacen.chelas.Almacen.Repositories.CajeroRepository;
import com.almacen.chelas.Almacen.Repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private CajeroRepository cajeroRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Venta>> findAll() {
        return ResponseEntity.ok(ventaRepository.findAll());
    }

    @GetMapping("/{ventaId}")
    public ResponseEntity<Venta> findById(@PathVariable Integer ventaId) {
        Optional<Venta> ventaOptional = ventaRepository.findById(ventaId);
        if (ventaOptional.isPresent()) {
            return ResponseEntity.ok(ventaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Venta newVenta, UriComponentsBuilder UriCmpBuilder) {

        Optional<Cajero> cajeroOptional = cajeroRepository.findById(newVenta.getCajero().getCajeroId());
        if (!cajeroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        newVenta.setCajero(cajeroOptional.get());
        Venta savedVenta = ventaRepository.save(newVenta);
        URI uri = UriCmpBuilder
                .path("venta/{ventaId}")
                .buildAndExpand(savedVenta.getVentaId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{ventaId}")
    public ResponseEntity<Void> update(@PathVariable Integer ventaId, @RequestBody Venta ventaAct) {
        Optional<Cajero> cajeroOptional = cajeroRepository.findById(ventaAct.getCajero().getCajeroId());
        if (!cajeroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Venta> ventaAnterior = ventaRepository.findById(ventaId);
        if (ventaAnterior != null) {
            ventaAct.setCajero(cajeroOptional.get());
            ventaAct.setVentaId(ventaAnterior.get().getVentaId());
            ventaRepository.save(ventaAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> delete(@PathVariable Integer ventaId) {
        Venta ventaAnterior = ventaRepository.findById(ventaId).get();
        if (ventaAnterior != null) {
            ventaRepository.deleteById(ventaId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
