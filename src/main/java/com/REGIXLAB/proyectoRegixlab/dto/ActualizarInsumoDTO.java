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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public @Min(value = 0, message = "La cantidad no puede ser negativa") Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@Min(value = 0, message = "La cantidad no puede ser negativa") Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getLugarAlmacenamiento() {
        return lugarAlmacenamiento;
    }

    public void setLugarAlmacenamiento(String lugarAlmacenamiento) {
        this.lugarAlmacenamiento = lugarAlmacenamiento;
    }

    public @Pattern(regexp = "^(rojo|verde|amarillo)$", message = "Estado inválido") String getEstadoSemaforizacion() {
        return estadoSemaforizacion;
    }

    public void setEstadoSemaforizacion(@Pattern(regexp = "^(rojo|verde|amarillo)$", message = "Estado inválido") String estadoSemaforizacion) {
        this.estadoSemaforizacion = estadoSemaforizacion;
    }
}
