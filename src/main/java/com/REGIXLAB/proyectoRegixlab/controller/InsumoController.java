package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.dto.NotificacionDTO;
import com.REGIXLAB.proyectoRegixlab.dto.SemaforizacionDTO;
import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import com.REGIXLAB.proyectoRegixlab.repository.InsumoRepository;
import com.REGIXLAB.proyectoRegixlab.util.ExcelExportadorInsumos;
import com.REGIXLAB.proyectoRegixlab.util.PdfExportadorInsumos;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.REGIXLAB.proyectoRegixlab.dto.ActualizarInsumoDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;

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

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Insumo> obtenerInsumoPorNombre(@PathVariable String nombre) {
        return insumoRepository.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/nombre/{nombre}")
    public ResponseEntity<String> editarInsumoPorNombre(
            @PathVariable String nombre,
            @RequestBody ActualizarInsumoDTO dto) {

        Optional<Insumo> insumoOpt = insumoRepository.findByNombre(nombre);
        if (!insumoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo con nombre '" + nombre + "' no encontrado");
        }

        Insumo insumo = insumoOpt.get();
        String colorAnterior = insumo.getEstadoSemaforizacion();

        if (dto.getNombre() != null) {
            insumo.setNombre(dto.getNombre());
        }
        if (dto.getCantidad() != null) {
            insumo.setCantidad(dto.getCantidad());
        }
        if (dto.getLugarAlmacenamiento() != null) {
            insumo.setLugarAlmacenamiento(dto.getLugarAlmacenamiento());
        }
        if (dto.getRegistroInvima() != null) {
            insumo.setRegistroInvima(dto.getRegistroInvima());
        }
        if (dto.getLote() != null) {
            insumo.setLote(dto.getLote());
        }
        if (dto.getFabricante() != null) {
            insumo.setFabricante(dto.getFabricante());
        }
        if (dto.getFechaApertura() != null) {
            insumo.setFechaApertura(dto.getFechaApertura());
        }
        if (dto.getFechaFinalizacion() != null) {
            insumo.setFechaFinalizacion(dto.getFechaFinalizacion());
        }
        if (dto.getFechaVencimiento() != null) {
            insumo.setFechaVencimiento(dto.getFechaVencimiento());
        }
        if (dto.getEstadoSemaforizacion() != null) {
            insumo.setEstadoSemaforizacion(dto.getEstadoSemaforizacion());
        }

        insumoRepository.save(insumo);

        // Enviar notificación si el color cambió
        String colorNuevo = insumo.getEstadoSemaforizacion();
        if (colorAnterior != null && colorNuevo != null && !colorAnterior.equals(colorNuevo)) {
            NotificacionDTO notificacion = new NotificacionDTO(
                    "El insumo '" + insumo.getNombre() + "' cambió a color " + colorNuevo,
                    colorNuevo,
                    insumo.getIdInsumo()
            );
            messagingTemplate.convertAndSend("/topic/notificaciones", notificacion);
        }

        return ResponseEntity.ok("Insumo actualizado exitosamente");
    }





    @DeleteMapping("/por-nombre/{nombre}")
    public ResponseEntity<String> eliminarInsumoPorNombre(@PathVariable String nombre) {
        Optional<Insumo> insumoOptional = insumoRepository.findByNombre(nombre);
        if (insumoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insumo con nombre \"" + nombre + "\" no encontrado");
        }
        insumoRepository.delete(insumoOptional.get());
        return ResponseEntity.ok("Insumo eliminado exitosamente");
    }


    @GetMapping("/resumen-semaforizacion")
    public ResponseEntity<List<SemaforizacionDTO>> getResumenSemaforizacion(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) String nombre
    ) {
        List<Insumo> insumos = insumoRepository.findAll();

        // Aplicar filtros
        List<Insumo> filtrados = insumos.stream()
                .filter(i -> color == null || color.equalsIgnoreCase(i.getEstadoSemaforizacion()))
                .filter(i -> fechaInicio == null || (i.getFechaRegistro() != null && !i.getFechaRegistro().isBefore(fechaInicio)))
                .filter(i -> fechaFin == null || (i.getFechaRegistro() != null && !i.getFechaRegistro().isAfter(fechaFin)))
                .filter(i -> nombre == null || i.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        int total = filtrados.size();

        // Agrupar por color (estado de semaforización)
        Map<String, List<Insumo>> agrupados = filtrados.stream()
                .collect(Collectors.groupingBy(i -> i.getEstadoSemaforizacion() == null ? "desconocido" : i.getEstadoSemaforizacion()));

        // Armar lista de resumen
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

    @GetMapping("/exportar/excel-filtrado")
    public void exportarExcelFiltradoPorFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerValue = "attachment; filename=insumos_filtrados_" + System.currentTimeMillis() + ".xlsx";
        response.setHeader("Content-Disposition", headerValue);

        // Buscar insumos filtrados
        List<Insumo> insumosFiltrados = insumoRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);

        // Usar el exportador
        ExcelExportadorInsumos exportador = new ExcelExportadorInsumos(insumosFiltrados);
        exportador.exportar(response);
    }


    @GetMapping("/exportar/pdf")
    public void exportarListadoDeInsumosPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");

        String headerValue = "attachment; filename=insumos_" + System.currentTimeMillis() + ".pdf";
        response.setHeader("Content-Disposition", headerValue);

        List<Insumo> listaInsumos = insumoRepository.findAll();

        PdfExportadorInsumos exportador = new PdfExportadorInsumos(listaInsumos);
        exportador.exportar(response);
    }

    @GetMapping("/exportar/pdf-filtrado")
    public void exportarPdfFiltradoPorFecha(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            HttpServletResponse response) throws IOException, DocumentException {

        response.setContentType("application/pdf");
        String headerValue = "attachment; filename=insumos_filtrados_" + System.currentTimeMillis() + ".pdf";
        response.setHeader("Content-Disposition", headerValue);

        List<Insumo> insumosFiltrados = insumoRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);

        PdfExportadorInsumos exportador = new PdfExportadorInsumos(insumosFiltrados);
        exportador.exportar(response);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


}
