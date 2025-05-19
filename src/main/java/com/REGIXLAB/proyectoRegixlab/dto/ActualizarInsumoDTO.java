package com.REGIXLAB.proyectoRegixlab.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class ActualizarInsumoDTO {

    private String nombre;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    private String lugarAlmacenamiento;

    @Pattern(regexp = "^(rojo|verde|amarillo)$", message = "Estado inválido")
    private String estadoSemaforizacion;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getLugarAlmacenamiento() {
        return lugarAlmacenamiento;
    }

    public void setLugarAlmacenamiento(String lugarAlmacenamiento) {
        this.lugarAlmacenamiento = lugarAlmacenamiento;
    }

    public String getEstadoSemaforizacion() {
        return estadoSemaforizacion;
    }

    public void setEstadoSemaforizacion(String estadoSemaforizacion) {
        this.estadoSemaforizacion = estadoSemaforizacion;
    }
}

