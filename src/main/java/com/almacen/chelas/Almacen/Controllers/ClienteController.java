package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.Cliente;
import com.almacen.chelas.Almacen.Models.Pedido;
import com.almacen.chelas.Almacen.Repositories.ClienteRepository;
import com.almacen.chelas.Almacen.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")

@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteRepository clientesRepository;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> findAll() {
        return ResponseEntity.ok(clientesRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer clienteId) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(clienteId);
        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cliente cliente, UriComponentsBuilder ucb) {
        Cliente savedCliente = clientesRepository.save(cliente);
        URI uri = ucb
                .path("cliente/{clienteId}")
                .buildAndExpand(savedCliente.getClienteId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> update(@PathVariable Integer clienteId, @RequestBody Cliente clienteAct) {
        Cliente clienteAnterior = clientesRepository.findById(clienteId).get();
        if (clienteAnterior != null) {
            clientesRepository.save(clienteAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Integer clienteId) {
        if (clientesRepository.findById(clienteId).get() == null) {
            return ResponseEntity.notFound().build();
        }
        clientesRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();
    }
}