package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;


@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "correoElectronico")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    @NotBlank(message = "El registro profesional es obligatorio")
    private String registroProfesional;
}
