package com.REGIXLAB.proyectoRegixlab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "insumo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "registro_invima")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long idInsumo;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El registro INVIMA es obligatorio")
    @Column(name = "registro_invima", nullable = false, unique = true)
    private String registroInvima;

    // Relación con Lote (Muchos insumos pueden pertenecer a un mismo lote)
    @ManyToOne
    @JoinColumn(name = "id_lote", nullable = false)
    private Lote lote;

    // Relación con Fabricante (Muchos insumos pueden tener un mismo fabricante)
    @ManyToOne
    @JoinColumn(name = "id_fabricante", nullable = false)
    private Fabricante fabricante;

    @Column(name = "lugar_almacenamiento")
    private String lugarAlmacenamiento;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 0;

    @Pattern(regexp = "Verde|Amarillo|Rojo|Vencido", message = "El estado debe ser Verde, Amarillo, Rojo o Vencido")
    @Column(name = "estado_semaforizacion")
    private String estadoSemaforizacion;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;
}
