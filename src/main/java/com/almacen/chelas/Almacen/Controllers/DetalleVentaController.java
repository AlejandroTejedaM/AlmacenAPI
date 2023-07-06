package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.DetalleVenta;
import com.almacen.chelas.Almacen.Models.Producto;
import com.almacen.chelas.Almacen.Models.Venta;
import com.almacen.chelas.Almacen.Repositories.DetalleVentaRepository;
import com.almacen.chelas.Almacen.Repositories.ProductoRepository;
import com.almacen.chelas.Almacen.Repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")

@RequestMapping("/detalleVenta")
public class DetalleVentaController {
    @Autowired
    DetalleVentaRepository detalleVentaRepository;
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<DetalleVenta>> findAll() {
        return ResponseEntity.ok(detalleVentaRepository.findAll());
    }

    @GetMapping("/{detalleVentaId}")
    public ResponseEntity<DetalleVenta> findById(@PathVariable Integer detalleVentaId) {
        Optional<DetalleVenta> detalleVentaOptional = detalleVentaRepository.findById(detalleVentaId);
        if (detalleVentaOptional.isPresent()) {
            return ResponseEntity.ok(detalleVentaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> create(@RequestBody DetalleVenta newDetalleVenta, UriComponentsBuilder UriCmpBuilder) {
        Optional<Venta> ventaOptional = ventaRepository.findById(newDetalleVenta.getVenta().getVentaId());
        if (!ventaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Producto> productoOptional = productoRepository.findById(newDetalleVenta.getProducto().getProductoId());
        if (!productoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        newDetalleVenta.setVenta(ventaOptional.get());
        newDetalleVenta.setProducto(productoOptional.get());
        DetalleVenta saveDetalleVenta = detalleVentaRepository.save(newDetalleVenta);
        URI uri = UriCmpBuilder
                .path("detalleVenta/{detalleVentaId}")
                .buildAndExpand(saveDetalleVenta.getDetalleVentaId())
                .toUri();
        return ResponseEntity.created(uri).body(saveDetalleVenta);
    }

    @PutMapping("/{detalleVentaId}")
    public ResponseEntity<Void> update(@PathVariable Integer detalleVentaId, @RequestBody DetalleVenta detalleVentaAct) {
        Optional<Venta> ventaOptional = ventaRepository.findById(detalleVentaAct.getVenta().getVentaId());
        if (!ventaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Producto> productoOptional = productoRepository.findById(detalleVentaAct.getProducto().getProductoId());
        if (!productoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<DetalleVenta> detalleVentaAnterior = detalleVentaRepository.findById(detalleVentaId);
        if (detalleVentaAnterior != null) {
            detalleVentaAct.setVenta(ventaOptional.get());
            detalleVentaAct.setProducto(productoOptional.get());
            detalleVentaRepository.save(detalleVentaAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{detalleVentaId}")
    public ResponseEntity<Void> delete(@PathVariable Integer detalleVentaId) {
        Optional<DetalleVenta> detalleVentaAnterior = detalleVentaRepository.findById(detalleVentaId);
        if (detalleVentaAnterior != null) {
            detalleVentaRepository.deleteById(detalleVentaId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
