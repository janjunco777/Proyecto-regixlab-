package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import com.REGIXLAB.proyectoRegixlab.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private InsumoRepository insumoRepository;

    @PostMapping
    public Insumo crearInsumo(@RequestBody Insumo insumo) {


        insumo.setFechaRegistro(LocalDate.now());
        return insumoRepository.save(insumo);
    }

    @GetMapping
    public List<Insumo> listarInsumos() {
        return insumoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Insumo obtenerInsumo(@PathVariable Long id) {
        return insumoRepository.findById(id).orElse(null);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editarInsumo(
            @PathVariable Long id,
            @RequestBody Insumo insumoActualizado) {

        // Buscar el insumo existente
        Optional<Insumo> insumoOpt = insumoRepository.findById(id);
        if (!insumoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo no encontrado");
        }

        Insumo insumo = insumoOpt.get();

        // Actualizar solo los campos permitidos
        if (insumoActualizado.getNombre() != null) {
            insumo.setNombre(insumoActualizado.getNombre());
        }
        if (insumoActualizado.getCantidad() != null) {
            insumo.setCantidad(insumoActualizado.getCantidad());
        }
        if (insumoActualizado.getLugarAlmacenamiento() != null) {
            insumo.setLugarAlmacenamiento(insumoActualizado.getLugarAlmacenamiento());
        }
        if (insumoActualizado.getEstadoSemaforizacion() != null) {
            insumo.setEstadoSemaforizacion(insumoActualizado.getEstadoSemaforizacion());
        }

        insumoRepository.save(insumo);

        return ResponseEntity.ok("Insumo actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInsumo(@PathVariable Long id) {
        // Verificar si el insumo existe
        if (!insumoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insumo con ID " + id + " no encontrado");
        }

        // Eliminar el insumo
        insumoRepository.deleteById(id);

        return ResponseEntity.ok("Insumo eliminado exitosamente");
    }

    @GetMapping("/agrupados-por-semaforizacion")
    public ResponseEntity<Map<String, List<Insumo>>> getInsumosAgrupadosPorSemaforizacion() {
        // Obtener todos los insumos
        List<Insumo> insumos = insumoRepository.findAll();

        // Agrupar por estado de semaforización
        Map<String, List<Insumo>> insumosAgrupados = insumos.stream()
                .collect(Collectors.groupingBy(Insumo::getEstadoSemaforizacion));

        return ResponseEntity.ok(insumosAgrupados);
    }

}
