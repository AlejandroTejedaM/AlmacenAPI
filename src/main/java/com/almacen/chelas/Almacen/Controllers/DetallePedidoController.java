package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.*;
import com.almacen.chelas.Almacen.Repositories.DetallePedidoRepository;
import com.almacen.chelas.Almacen.Repositories.PedidoRepository;
import com.almacen.chelas.Almacen.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")

@RequestMapping("/detallePedido")
public class DetallePedidoController {
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<DetallePedido>> findAll() {
        return ResponseEntity.ok(detallePedidoRepository.findAll());
    }

    @GetMapping("/{detallePedidoId}")
    public ResponseEntity<DetallePedido> findById(@PathVariable Integer detallePedidoId) {
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(detallePedidoId);
        if (detallePedidoOptional.isPresent()) {
            return ResponseEntity.ok(detallePedidoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DetallePedido newDetallePedido, UriComponentsBuilder UriCmpBuilder) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(newDetallePedido.getPedido().getPedidoID());
        if (!pedidoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Producto> productoOptional = productoRepository.findById(newDetallePedido.getProducto().getProductoId());
        if (!productoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        newDetallePedido.setPedido(pedidoOptional.get());
        newDetallePedido.setProducto(productoOptional.get());
        DetallePedido saveDetallePedido = detallePedidoRepository.save(newDetallePedido);
        URI uri = UriCmpBuilder.path("detallePedido/{detallePedidoId}").buildAndExpand(saveDetallePedido.getDetallePedidoId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{detallePedidoId}")
    public ResponseEntity<Void> update(@PathVariable Integer detallePedidoId, @RequestBody DetallePedido detallePedidoActu) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(detallePedidoActu.getPedido().getPedidoID());
        if (!pedidoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Producto> productoOptional = productoRepository.findById(detallePedidoActu.getProducto().getProductoId());
        if (!productoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<DetallePedido> detallePedidoAnterior = detallePedidoRepository.findById(detallePedidoId);
        if (detallePedidoAnterior != null) {
            detallePedidoActu.setPedido(pedidoOptional.get());
            detallePedidoActu.setProducto(productoOptional.get());
            detallePedidoRepository.save(detallePedidoActu);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{detallePedidoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer detallePedidoId) {
        Optional<DetallePedido> detallePedidoAnterior = detallePedidoRepository.findById(detallePedidoId);
        if (detallePedidoAnterior != null) {
            detallePedidoRepository.deleteById(detallePedidoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
