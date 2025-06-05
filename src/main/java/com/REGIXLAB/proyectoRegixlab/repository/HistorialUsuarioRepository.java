package com.REGIXLAB.proyectoRegixlab.repository;

import com.REGIXLAB.proyectoRegixlab.model.HistorialUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialUsuarioRepository extends JpaRepository<HistorialUsuario, Long> {
    List<HistorialUsuario> findByIdentificacionUsuario(String identificacionUsuario);
}
