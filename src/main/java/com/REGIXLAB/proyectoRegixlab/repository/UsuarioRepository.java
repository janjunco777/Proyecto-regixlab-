package com.REGIXLAB.proyectoRegixlab.repository;

import com.REGIXLAB.proyectoRegixlab.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
