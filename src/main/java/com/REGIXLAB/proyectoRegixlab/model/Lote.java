package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "Lote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLote;

    @Column(name = "numero_lote", unique = true, nullable = false)
    @NotBlank(message = "El número de lote es obligatorio")
    private String numeroLote;

    @Column(name = "fecha_fabricacion")
    private LocalDate fechaFabricacion;

    @Column(name = "fecha_vencimiento", nullable = false)
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_fabricante", nullable = false)
    private Fabricante fabricante;

    @OneToOne
    @JoinColumn(name = "registro_invima")
    private Insumo insumo;  // Suponiendo que es una relación uno a uno
}
