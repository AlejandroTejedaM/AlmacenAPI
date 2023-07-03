package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.Pedido;
import com.almacen.chelas.Almacen.Models.Cliente;
import com.almacen.chelas.Almacen.Repositories.ClienteRepository;
import com.almacen.chelas.Almacen.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoRepository pedidosRepository;
    @Autowired
    ClienteRepository clientesRepository;

    @GetMapping
    public ResponseEntity<Iterable<Pedido>> findAll() {
        return ResponseEntity.ok(pedidosRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer pedidoId) {
        Optional<Pedido> pedidoOptional = pedidosRepository.findById(pedidoId);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Pedido pedido, UriComponentsBuilder ucb) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(pedido.getCliente().getClienteId());
        if (!clienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        pedido.setCliente(clienteOptional.get());
        Pedido savedPedido = pedidosRepository.save(pedido);
        URI uri = ucb.path("pedido/{pedidoId}").buildAndExpand(savedPedido.getPedidoID()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Void> update(@PathVariable Integer pedidoId, @RequestBody Pedido pedidoAct) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(pedidoAct.getCliente().getClienteId());
        if (!clienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Pedido> pedidoOptional = pedidosRepository.findById(pedidoId);
        if (pedidoOptional != null) {
            pedidoAct.setPedidoID(pedidoOptional.get().getPedidoID());
            pedidoAct.setCliente(clienteOptional.get());
            pedidosRepository.save(pedidoAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer pedidoId) {
        if (pedidosRepository.findById(pedidoId).get() == null) {
            return ResponseEntity.notFound().build();
        }
        pedidosRepository.deleteById(pedidoId);
        return ResponseEntity.noContent().build();
    }

}