package com.REGIXLAB.proyectoRegixlab.controller;

import com.REGIXLAB.proyectoRegixlab.dto.UsuarioDTO;
import com.REGIXLAB.proyectoRegixlab.entity.Usuario;
import com.REGIXLAB.proyectoRegixlab.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
