package com.REGIXLAB.proyectoRegixlab.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fabricante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fabricante")
    private Long idFabricante;

    @Column(name = "nombre_fabricante", nullable = false)
    private String nombreFabricante;

    @Column(name = "direccion_fabricante")
    private String direccionFabricante;

    @Column(name = "correo_fabricante")
    private String correoFabricante;

    @Column(name = "telefono_fabricante")
    private String telefonoFabricante;

    @Column(name = "telefono_2_fabricante")
    private String telefono2Fabricante;
}
