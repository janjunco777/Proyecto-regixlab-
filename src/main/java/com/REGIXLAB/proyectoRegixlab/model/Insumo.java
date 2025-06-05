package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    @Column(name = "registro_invima", nullable = false, length = 50)
    @NotBlank(message = "El registro INVIMA es obligatorio")
    private String registroInvima;

    @Column(name = "lugar_almacenamiento", length = 100)
    private String lugarAlmacenamiento;

    @Column(nullable = false)
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @Column(name = "estado_semaforizacion", length = 20)
    private String estadoSemaforizacion; // Calculado automáticamente

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    @Column(name = "fecha_vencimiento")
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate fechaVencimiento;

    @Column(length = 50)
    @NotBlank(message = "El lote es obligatorio")
    private String lote;

    @Column(length = 100)
    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;

    public Insumo() {}

    // === MÉTODO PARA CALCULAR LA SEMAFORIZACIÓN AUTOMÁTICAMENTE ===
    @PrePersist
    @PreUpdate
    public void calcularEstadoSemaforizacion() {
        if (fechaVencimiento == null) {
            estadoSemaforizacion = "desconocido";
            return;
        }

        LocalDate hoy = LocalDate.now();
        long mesesRestantes = ChronoUnit.MONTHS.between(hoy.withDayOfMonth(1), fechaVencimiento.withDayOfMonth(1));

        if (fechaVencimiento.isBefore(hoy)) {
            estadoSemaforizacion = "gris"; // Vencido
        } else if (mesesRestantes < 3) {
            estadoSemaforizacion = "rojo";
        } else if (mesesRestantes < 6) {
            estadoSemaforizacion = "amarillo";
        } else {
            estadoSemaforizacion = "verde";
        }
    }

    // Getters y Setters (sin cambios)
    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(String registroInvima) {
        this.registroInvima = registroInvima;
    }

    public String getLugarAlmacenamiento() {
        return lugarAlmacenamiento;
    }

    public void setLugarAlmacenamiento(String lugarAlmacenamiento) {
        this.lugarAlmacenamiento = lugarAlmacenamiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstadoSemaforizacion() {
        return estadoSemaforizacion;
    }

    public void setEstadoSemaforizacion(String estadoSemaforizacion) {
        this.estadoSemaforizacion = estadoSemaforizacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
}

