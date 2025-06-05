package com.REGIXLAB.proyectoRegixlab.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ActualizarInsumoDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    private String lugarAlmacenamiento;

    @NotBlank(message = "El registro INVIMA es obligatorio")
    private String registroInvima;

    @NotBlank(message = "El Lote es obligatorio")
    private String lote;

    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;

    private LocalDate fechaApertura;

    private LocalDate fechaFinalizacion;

    @NotNull(message = "La fecha de vencimiento no puede ser nula")
    private LocalDate fechaVencimiento;

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

    public String getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(String registroInvima) {
        this.registroInvima = registroInvima;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstadoSemaforizacion() {
        return getEstadoSemaforizacion();
    }
}


