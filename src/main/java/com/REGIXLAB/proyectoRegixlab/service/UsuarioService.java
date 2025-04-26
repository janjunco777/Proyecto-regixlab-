package com.REGIXLAB.proyectoRegixlab.service;

import com.REGIXLAB.proyectoRegixlab.dto.UsuarioDTO;
import com.REGIXLAB.proyectoRegixlab.entity.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(UsuarioDTO usuarioDTO);
}
