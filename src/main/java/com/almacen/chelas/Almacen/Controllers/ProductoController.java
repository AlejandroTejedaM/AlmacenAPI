package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.Producto;
import com.almacen.chelas.Almacen.Models.TipoCerveza;
import com.almacen.chelas.Almacen.Repositories.ProductoRepository;
import com.almacen.chelas.Almacen.Repositories.TipoCervezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    TipoCervezaRepository tipoCervezaRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Producto>> findAll() {
        return ResponseEntity.ok(productoRepository.findAll());
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> findById(@PathVariable Integer productoId) {
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Producto newProducto, UriComponentsBuilder UriCmpBuilder) {

        Optional<TipoCerveza> tipoCervezaOptional = tipoCervezaRepository.findById(newProducto.getTipoCerveza().getTipoCervezaId());
        if (!tipoCervezaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        newProducto.setTipoCerveza(tipoCervezaOptional.get());
        Producto savedproducto = productoRepository.save(newProducto);
        URI uri = UriCmpBuilder
                .path("producto/{productoId}")
                .buildAndExpand(savedproducto.getProductoId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Void> update(@PathVariable Integer productoId, @RequestBody Producto productoAct) {
        Optional<TipoCerveza> tipoCervezaOptional = tipoCervezaRepository.findById(productoAct.getTipoCerveza().getTipoCervezaId());
        if (!tipoCervezaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Producto> productoAnterior = productoRepository.findById(productoId);
        if (productoAnterior != null) {
            productoAct.setTipoCerveza(tipoCervezaOptional.get());
            productoAct.setProductoId(productoAnterior.get().getProductoId());
            productoRepository.save(productoAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer productoId) {
        Producto autoAnterior = productoRepository.findById(productoId).get();
        if (autoAnterior != null) {
            productoRepository.deleteById(productoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
