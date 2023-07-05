package com.almacen.chelas.Almacen.Controllers;

import com.almacen.chelas.Almacen.Models.TipoCerveza;
import com.almacen.chelas.Almacen.Repositories.TipoCervezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.UriComponentsContributor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")

@RequestMapping("/tipoCerveza")
public class TipoCervezaController {

    @Autowired
    TipoCervezaRepository tipoCervezaRepository;

    @GetMapping()
    public ResponseEntity<Iterable<TipoCerveza>> findAll() {
        return ResponseEntity.ok(tipoCervezaRepository.findAll());
    }

    @GetMapping("/{tipocervezaId}")
    public ResponseEntity<TipoCerveza> findById(@PathVariable Integer tipocervezaId) {
        Optional<TipoCerveza> tipoCervezaOptional = tipoCervezaRepository.findById(tipocervezaId);
        if (tipoCervezaOptional.isPresent()) {
            return ResponseEntity.ok(tipoCervezaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TipoCerveza tipoCerveza, UriComponentsBuilder ucb) {
        TipoCerveza savedTipoCerveza = tipoCervezaRepository.save(tipoCerveza);
        URI uri = ucb.path("tipoCerveza/{tipoCervezaId}").buildAndExpand(savedTipoCerveza.getTipoCervezaId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{tipoCervezaId}")
    public ResponseEntity<Void> update(@PathVariable Integer tipoCervezaId, @RequestBody TipoCerveza TipoCervezaAct) {
        Optional<TipoCerveza> tipoCervezaOptional = tipoCervezaRepository.findById(TipoCervezaAct.getTipoCervezaId());
        if (!tipoCervezaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tipoCervezaId}")
    public ResponseEntity<Void> delete(@PathVariable Integer tipoCervezaId) {
        if (tipoCervezaRepository.findById(tipoCervezaId).get() == null) {
            return ResponseEntity.notFound().build();
        }
        tipoCervezaRepository.deleteById(tipoCervezaId);
        return ResponseEntity.noContent().build();
    }
}
