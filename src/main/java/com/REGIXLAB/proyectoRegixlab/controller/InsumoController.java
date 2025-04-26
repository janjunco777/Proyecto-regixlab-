package com.REGIXLAB.proyectoRegixlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import com.REGIXLAB.proyectoRegixlab.repository.InsumoRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/insumos")
public class InsumoController {

    @Autowired
    private InsumoRepository insumoRepository;

    @PostMapping
    public ResponseEntity<Insumo> crearInsumo(@Valid @RequestBody Insumo insumo) {
        return ResponseEntity.ok(insumoRepository.save(insumo));
    }

    @GetMapping
    public ResponseEntity<List<Insumo>> listarInsumos() {
        return ResponseEntity.ok(insumoRepository.findAll());
    }
}
