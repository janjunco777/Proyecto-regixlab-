package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios") // Asegúrate que coincida con tu tabla en SQL Server
public class Usuario {


    @Column(name = "nombre", length = 50, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "primer_apellido", length = 50, nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;
    @Id
    @Column(name = "identificacion", length = 50, unique = true, nullable = false)
    @NotBlank(message = "La identificacion es obligatorio")
    private String identificacion;

    @Column(name = "correo_electronico", length = 100, unique = true, nullable = false)
    @NotBlank(message = "El correo_electronico es obligatorio")
    private String correoElectronico;

    @Column(name = "contrasena", length = 100, nullable = false)
    @NotBlank(message = "La contrasena es obligatorio")
    private String contrasena;

    @Column(name = "registro_profesional", length = 50)
    private String registroProfesional;

    @Column(name = "intentos_fallidos")
    private int intentosFallidos = 0;

    @Column(name = "cuenta_bloqueada")
    private boolean cuentaBloqueada = false;

    @Column(name = "fecha_bloqueo")
    private LocalDateTime fechaBloqueo;



    public Usuario() {}

    public Usuario(String nombre, String primerApellido,
                   String segundoApellido, String identificacion,
                   String correoElectronico, String contrasena,
                   String registroProfesional) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.identificacion = identificacion;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.registroProfesional = registroProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRegistroProfesional() {
        return registroProfesional;
    }

    public void setRegistroProfesional(String registroProfesional) {
        this.registroProfesional = registroProfesional;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public LocalDateTime getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(LocalDateTime fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }
}
