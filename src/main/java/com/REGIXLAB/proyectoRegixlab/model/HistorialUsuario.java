package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_usuario")
public class HistorialUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion_usuario")
    private String identificacionUsuario;

    @Column(name = "campo_modificado")
    private String campoModificado;

    @Column(name = "valor_anterior")
    private String valorAnterior;

    @Column(name = "valor_nuevo")
    private String valorNuevo;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    public HistorialUsuario() {}

    public HistorialUsuario(String identificacionUsuario, String campoModificado,
                            String valorAnterior, String valorNuevo, LocalDateTime fechaModificacion) {
        this.identificacionUsuario = identificacionUsuario;
        this.campoModificado = campoModificado;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.fechaModificacion = fechaModificacion;
    }

    // Getters y Setters
}

