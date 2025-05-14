package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long idInsumo;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "registro_invima", length = 50)
    @NotBlank(message = "El registro invima es obligatorio")
    private String registroInvima;

    @Column(name = "lugar_almacenamiento", length = 100)
    private String lugarAlmacenamiento;

    @Column(nullable = false)
    @NotBlank(message = "La cantidad es obligatorio")
    private Integer cantidad;

    @Column(name = "estado_semaforizacion", length = 20)
    private String estadoSemaforizacion; // "rojo", "verde" o "amarillo"

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    @Column(length = 50)
    @NotBlank(message = "El lote es obligatorio")
    private String lote;

    @Column(length = 100)
    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;

    public Insumo() {}

    public Insumo(Long idInsumo, String nombre, String registroInvima, Integer cantidad,
                  String lugarAlmacenamiento, String estadoSemaforizacion, LocalDate fechaRegistro,
                  LocalDate fechaFinalizacion, LocalDate fechaApertura, String lote, String fabricante) {
        this.idInsumo = idInsumo;
        this.nombre = nombre;
        this.registroInvima = registroInvima;
        this.cantidad = cantidad;
        this.lugarAlmacenamiento = lugarAlmacenamiento;
        this.estadoSemaforizacion = estadoSemaforizacion;
        this.fechaRegistro = fechaRegistro;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaApertura = fechaApertura;
        this.lote = lote;
        this.fabricante = fabricante;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El registro invima es obligatorio") String getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(@NotBlank(message = "El registro invima es obligatorio") String registroInvima) {
        this.registroInvima = registroInvima;
    }

    public String getLugarAlmacenamiento() {
        return lugarAlmacenamiento;
    }

    public void setLugarAlmacenamiento(String lugarAlmacenamiento) {
        this.lugarAlmacenamiento = lugarAlmacenamiento;
    }

    public @NotBlank(message = "La cantidad es obligatorio") Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@NotBlank(message = "La cantidad es obligatorio") Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoSemaforizacion() {
        return estadoSemaforizacion;
    }

    public void setEstadoSemaforizacion(String estadoSemaforizacion) {
        this.estadoSemaforizacion = estadoSemaforizacion;
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

    public @NotBlank(message = "El lote es obligatorio") String getLote() {
        return lote;
    }

    public void setLote(@NotBlank(message = "El lote es obligatorio") String lote) {
        this.lote = lote;
    }

    public @NotBlank(message = "El fabricante es obligatorio") String getFabricante() {
        return fabricante;
    }

    public void setFabricante(@NotBlank(message = "El fabricante es obligatorio") String fabricante) {
        this.fabricante = fabricante;
    }
}