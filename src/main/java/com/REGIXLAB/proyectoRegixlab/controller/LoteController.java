package com.REGIXLAB.proyectoRegixlab.controller;
import com.REGIXLAB.proyectoRegixlab.model.Lote;
import com.REGIXLAB.proyectoRegixlab.repository.LoteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lote")

public class LoteController {

    @Autowired
    private LoteRepository loteRepository;

    // Obtener todos los lotes
    @GetMapping
    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }

    // Crear un nuevo lote
    @PostMapping
    public ResponseEntity<?> crearLote(@Valid @RequestBody Lote lote) {
        return ResponseEntity.ok(loteRepository.save(lote));
    }

    // Obtener lote por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLote(@PathVariable Long id) {
        Optional<Lote> lote = loteRepository.findById(id);
        return lote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un lote
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLote(@PathVariable Long id, @Valid @RequestBody Lote detallesLote) {
        return loteRepository.findById(id).map(lote -> {
            lote.setNumeroLote(detallesLote.getNumeroLote());
            lote.setFechaFabricacion(detallesLote.getFechaFabricacion());
            lote.setFechaVencimiento(detallesLote.getFechaVencimiento());
            lote.setFabricante(detallesLote.getFabricante());
            return ResponseEntity.ok(loteRepository.save(lote));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar lote
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLote(@PathVariable Long id) {
        return loteRepository.findById(id).map(lote -> {
            loteRepository.delete(lote);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
