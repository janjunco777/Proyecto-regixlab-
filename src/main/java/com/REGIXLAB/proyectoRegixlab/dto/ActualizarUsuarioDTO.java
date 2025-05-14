package com.REGIXLAB.proyectoRegixlab.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ActualizarUsuarioDTO {
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres")
    private String primerApellido;

    @Size(max = 50, message = "El segundo apellido no puede exceder 50 caracteres")
    private String segundoApellido;

    @Email(message = "Debe ser un correo electrónico válido")
    private String correoElectronico;

    @Size(max = 50, message = "El registro profesional no puede exceder 50 caracteres")
    private String registroProfesional;

    public @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres") String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(@Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres") String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public @Size(max = 50, message = "El segundo apellido no puede exceder 50 caracteres") String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(@Size(max = 50, message = "El segundo apellido no puede exceder 50 caracteres") String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public @Email(message = "Debe ser un correo electrónico válido") String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(@Email(message = "Debe ser un correo electrónico válido") String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public @Size(max = 50, message = "El registro profesional no puede exceder 50 caracteres") String getRegistroProfesional() {
        return registroProfesional;
    }

    public void setRegistroProfesional(@Size(max = 50, message = "El registro profesional no puede exceder 50 caracteres") String registroProfesional) {
        this.registroProfesional = registroProfesional;
    }
}