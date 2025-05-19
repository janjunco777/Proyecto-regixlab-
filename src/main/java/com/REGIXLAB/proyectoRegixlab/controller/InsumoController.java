package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.dto.SemaforizacionDTO;
import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import com.REGIXLAB.proyectoRegixlab.repository.InsumoRepository;
import com.REGIXLAB.proyectoRegixlab.util.ExcelExportadorInsumos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.REGIXLAB.proyectoRegixlab.dto.ActualizarInsumoDTO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
            @RequestBody ActualizarInsumoDTO dto) {

        Optional<Insumo> insumoOpt = insumoRepository.findById(id);
        if (!insumoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo no encontrado");
        }

        Insumo insumo = insumoOpt.get();

        if (dto.getNombre() != null) {
            insumo.setNombre(dto.getNombre());
        }
        if (dto.getCantidad() != null) {
            insumo.setCantidad(dto.getCantidad());
        }
        if (dto.getLugarAlmacenamiento() != null) {
            insumo.setLugarAlmacenamiento(dto.getLugarAlmacenamiento());
        }
        if (dto.getEstadoSemaforizacion() != null) {
            insumo.setEstadoSemaforizacion(dto.getEstadoSemaforizacion());
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

    @GetMapping("/resumen-semaforizacion")
    public ResponseEntity<List<SemaforizacionDTO>> getResumenSemaforizacion() {
        List<Insumo> insumos = insumoRepository.findAll();
        int total = insumos.size();

        Map<String, List<Insumo>> agrupados = insumos.stream()
                .collect(Collectors.groupingBy(i -> i.getEstadoSemaforizacion() == null ? "desconocido" : i.getEstadoSemaforizacion()));

        List<SemaforizacionDTO> resumen = agrupados.entrySet().stream()
                .map(entry -> new SemaforizacionDTO(entry.getKey(), entry.getValue(), total))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/exportar/excel")
    public ResponseEntity<byte[]> exportarInsumosExcel() {
        try {
            List<Insumo> insumos = insumoRepository.findAll();
            ByteArrayInputStream stream = ExcelExportadorInsumos.insumosAExcel(insumos);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=insumos.xlsx")
                    .body(stream.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
