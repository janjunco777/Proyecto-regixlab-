package com.REGIXLAB.proyectoRegixlab.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "insumo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "registroInvima")
})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Insumo;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String registro_Invima;

    @Min(0)
    @Column(nullable = false)
    private int cantidad;

    @Pattern(regexp = "Verde|Amarillo|Rojo|Vencido")
    private String estado_Semaforizacion;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "idFabricante")
    private Fabricante fabricante;
}
