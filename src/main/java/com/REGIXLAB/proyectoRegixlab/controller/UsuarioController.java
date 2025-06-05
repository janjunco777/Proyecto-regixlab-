package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.dto.ActualizarUsuarioDTO;
import com.REGIXLAB.proyectoRegixlab.dto.CambioContrasenaDTO;
import com.REGIXLAB.proyectoRegixlab.model.HistorialUsuario;
import com.REGIXLAB.proyectoRegixlab.model.Usuario;
import com.REGIXLAB.proyectoRegixlab.repository.HistorialUsuarioRepository;
import com.REGIXLAB.proyectoRegixlab.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HistorialUsuarioRepository historialUsuarioRepository;



    // Registrar nuevo usuario
    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar usuario por identificación
    @GetMapping("/por-identificacion/{identificacion}")
    public ResponseEntity<Usuario> buscarPorIdentificacion(
            @PathVariable String identificacion) {

        Optional<Usuario> usuario = usuarioRepository.findByIdentificacion(identificacion);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }


    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(
            @RequestParam String identificacion,
            @RequestBody CambioContrasenaDTO cambioContrasenaDTO) {

        // Buscar usuario por identificación
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(identificacion);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();


        if (!usuario.getContrasena().equals(cambioContrasenaDTO.getContrasenaActual())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contraseña actual incorrecta");
        }


        if (!cambioContrasenaDTO.getNuevaContrasena().equals(cambioContrasenaDTO.getConfirmacionContrasena())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las contraseñas nuevas no coinciden");
        }

        // Actualizar contraseña
        usuario.setContrasena(cambioContrasenaDTO.getNuevaContrasena());
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }


    //actualizar usuarios
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUsuarioDTO usuarioDTO) {

        // Buscar usuario existente
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();

        // Actualizar campos (solo si vienen en el DTO)
        if (usuarioDTO.getNombre() != null) {
            usuario.setNombre(usuarioDTO.getNombre());
        }
        if (usuarioDTO.getPrimerApellido() != null) {
            usuario.setPrimerApellido(usuarioDTO.getPrimerApellido());
        }
        if (usuarioDTO.getSegundoApellido() != null) {
            usuario.setSegundoApellido(usuarioDTO.getSegundoApellido());
        }
        if (usuarioDTO.getCorreoElectronico() != null &&
                !usuarioDTO.getCorreoElectronico().equals(usuario.getCorreoElectronico())) {

            if (usuarioRepository.existsByCorreoElectronico(usuarioDTO.getCorreoElectronico())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El correo ya está registrado");
            }
            usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        }
        if (usuarioDTO.getRegistroProfesional() != null) {
            usuario.setRegistroProfesional(usuarioDTO.getRegistroProfesional());
        }

        // Guardar cambios
        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        //HISTORIAL DE USUARIO MODIFICADO
        if (usuarioDTO.getNombre() != null && !usuarioDTO.getNombre().equals(usuario.getNombre())) {
            historialUsuarioRepository.save(new HistorialUsuario(
                    usuario.getIdentificacion(), "nombre",
                    usuario.getNombre(), usuarioDTO.getNombre(),
                    LocalDateTime.now()
            ));
            usuario.setNombre(usuarioDTO.getNombre());
        }

        return ResponseEntity.ok(usuarioActualizado);
    }
    //ENDPOINT HISTORIAL
    @GetMapping("/{identificacion}/historial")
    public ResponseEntity<List<HistorialUsuario>> obtenerHistorial(@PathVariable String identificacion) {
        List<HistorialUsuario> historial = historialUsuarioRepository.findByIdentificacionUsuario(identificacion);
        return ResponseEntity.ok(historial);
    }

}
