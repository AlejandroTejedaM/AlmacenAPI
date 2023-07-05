package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.AutorizacionPago;
import com.almacen.chelas.Almacen.Models.Venta;
import com.almacen.chelas.Almacen.Repositories.AutorizacionPagoRepository;
import com.almacen.chelas.Almacen.Repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/autorizacionPago")
public class AutorizacionPagoController {
    @Autowired
    AutorizacionPagoRepository autorizacionPagoRepository;
    @Autowired
    VentaRepository ventaRepository;

    @GetMapping()
    public ResponseEntity<Iterable<AutorizacionPago>> findAll() {

        return ResponseEntity.ok(autorizacionPagoRepository.findAll());
    }

    @GetMapping("/{autorizacionId}")
    public ResponseEntity<AutorizacionPago> findById(@PathVariable Integer autorizacionId) {
        Optional<AutorizacionPago> autorizacionOptional = autorizacionPagoRepository.findById(autorizacionId);
        if (autorizacionOptional.isPresent()) {
            return ResponseEntity.ok(autorizacionOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AutorizacionPago autopago, UriComponentsBuilder ucb) {
        Optional<Venta> ventaOptional = ventaRepository.findById(autopago.getVenta().getVentaId());
        if (!ventaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        autopago.setVenta(ventaOptional.get());
        AutorizacionPago savedAutoPago = autorizacionPagoRepository.save(autopago);
        URI uri = ucb
                .path("autorizacionPago/{autorizacionId}")
                .buildAndExpand(savedAutoPago.getAutorizacionId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{autorizacionId}")
    public ResponseEntity<Void> update(@PathVariable Integer idAutorizacionPago, @RequestBody AutorizacionPago autopago) {
        Optional<Venta> ventaOptional = ventaRepository.findById(autopago.getVenta().getVentaId());
        if (!ventaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<AutorizacionPago> autorizacionPagoOptional = autorizacionPagoRepository.findById(idAutorizacionPago);
        if (!autorizacionPagoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        autopago.setVenta(ventaOptional.get());
        autopago.setAutorizacionId(autorizacionPagoOptional.get().getAutorizacionId());
        autorizacionPagoRepository.save(autopago);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{autorizacionId}")
    public ResponseEntity<Void> delete(@PathVariable Integer idAutorizacionPago) {
        if (autorizacionPagoRepository.findById(idAutorizacionPago).get() == null) {
            return ResponseEntity.notFound().build();
        }
        autorizacionPagoRepository.deleteById(idAutorizacionPago);
        return ResponseEntity.noContent().build();
    }
}