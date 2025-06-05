package com.REGIXLAB.proyectoRegixlab.service;

import com.REGIXLAB.proyectoRegixlab.model.Usuario;
import com.REGIXLAB.proyectoRegixlab.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        if (usuario.isCuentaBloqueada()) {
            throw new LockedException("La cuenta está bloqueada por múltiples intentos fallidos");
        }

        return new User(usuario.getCorreoElectronico(), usuario.getContrasena(), new ArrayList<>());
    }
    public void registrarIntentoFallido(String correo) {
        Optional<Usuario> optUsuario = Optional.ofNullable(usuarioRepository.findByCorreoElectronico(correo));
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();

            int intentos = usuario.getIntentosFallidos() + 1;
            usuario.setIntentosFallidos(intentos);

            if (intentos >= 4) {
                usuario.setCuentaBloqueada(true);
                usuario.setFechaBloqueo(LocalDateTime.now());
            }

            usuarioRepository.save(usuario);
        }
    }


}

