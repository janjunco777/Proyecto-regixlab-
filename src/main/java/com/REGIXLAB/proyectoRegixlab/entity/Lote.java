package com.REGIXLAB.proyectoRegixlab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long idLote;

    @Column(name = "numero_lote", nullable = false, unique = true)
    private String numeroLote;

    @Column(name = "fecha_fabricacion")
    private LocalDate fechaFabricacion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;


    @ManyToOne
    @JoinColumn(name = "id_fabricante", nullable = false)
    private Fabricante fabricante;


    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Insumo> insumos;
}
