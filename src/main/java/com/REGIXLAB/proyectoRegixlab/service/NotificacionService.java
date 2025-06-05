package com.REGIXLAB.proyectoRegixlab.service;

import com.REGIXLAB.proyectoRegixlab.model.Notificacion;
import com.REGIXLAB.proyectoRegixlab.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion(String correo, String titulo, String mensaje, String tipo) {
        Notificacion n = new Notificacion();
        n.setDestinatarioCorreo(correo);
        n.setTitulo(titulo);
        n.setMensaje(mensaje);
        n.setTipo(tipo);
        return notificacionRepository.save(n);
    }

    public List<Notificacion> obtenerNoLeidas(String correo) {
        return notificacionRepository.findByDestinatarioCorreoAndLeidoFalse(correo);
    }

    public void marcarComoLeida(Long id) {
        notificacionRepository.findById(id).ifPresent(n -> {
            n.setLeido(true);
            notificacionRepository.save(n);
        });
    }
}

