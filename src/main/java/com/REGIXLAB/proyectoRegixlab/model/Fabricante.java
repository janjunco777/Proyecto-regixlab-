package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "fabricante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFabricante;

    @NotBlank
    private String nombreFabricante;

    @NotBlank
    private String direccionFabricante;

    @Email
    private String correoFabricante;

    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos")
    private String telefonoFabricante;

    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos")
    private String telefono2Fabricante;
}
