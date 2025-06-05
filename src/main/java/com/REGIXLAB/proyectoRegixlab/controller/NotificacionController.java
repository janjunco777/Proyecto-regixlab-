package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.model.Notificacion;
import com.REGIXLAB.proyectoRegixlab.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/pendientes/{correo}")
    public List<Notificacion> getPendientes(@PathVariable String correo) {
        return notificacionService.obtenerNoLeidas(correo);
    }

    @PostMapping("/marcar-leida/{id}")
    public void marcarLeida(@PathVariable Long id) {
        notificacionService.marcarComoLeida(id);
    }
}
