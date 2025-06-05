package com.REGIXLAB.proyectoRegixlab.repository;

import com.REGIXLAB.proyectoRegixlab.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByDestinatarioCorreoAndLeidoFalse(String correo);
}
