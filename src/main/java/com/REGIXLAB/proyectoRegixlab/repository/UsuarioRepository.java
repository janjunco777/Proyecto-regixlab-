package com.REGIXLAB.proyectoRegixlab.repository;

import com.REGIXLAB.proyectoRegixlab.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByCorreoElectronico(String correoElectronico);


    Optional<Usuario> findByIdentificacion(String identificacion);



    boolean existsByCorreoElectronico(String correoElectronico);


    boolean existsByIdentificacion(String identificacion);
}