package com.REGIXLAB.proyectoRegixlab.service.impl;
import com.REGIXLAB.proyectoRegixlab.dto.UsuarioDTO;
import com.REGIXLAB.proyectoRegixlab.entity.Usuario;
import com.REGIXLAB.proyectoRegixlab.repository.UsuarioRepository;
import com.REGIXLAB.proyectoRegixlab.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario registrarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        return usuarioRepository.save(usuario);
    }
}
