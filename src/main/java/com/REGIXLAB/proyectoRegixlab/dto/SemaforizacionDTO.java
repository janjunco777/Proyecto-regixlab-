package com.REGIXLAB.proyectoRegixlab.dto;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import java.util.List;

public class SemaforizacionDTO {

    private final String color;
    private final List<Insumo> insumos;
    private final int totalInsumos;
    private final double porcentaje; // Porcentaje respecto al total general

    /**
     * Constructor que inicializa el resumen de semaforización.
     *
     * @param color         Color de semaforización (rojo, amarillo, verde)
     * @param insumos       Lista de insumos agrupados por color
     * @param totalGeneral  Total de insumos después de aplicar los filtros
     */
    public SemaforizacionDTO(String color, List<Insumo> insumos, int totalGeneral) {
        this.color = color;
        this.insumos = insumos;
        this.totalInsumos = insumos.size();
        this.porcentaje = totalGeneral > 0 ? (totalInsumos * 100.0) / totalGeneral : 0.0;
    }

    public String getColor() {
        return color;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public int getTotalInsumos() {
        return totalInsumos;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Indica si un grupo de semaforización en rojo no tiene insumos, lo que puede requerir atención.
     *
     * @return true si es grupo rojo sin insumos, false en caso contrario.
     */
    public boolean necesitaAtencion() {
        return totalInsumos == 0 && "rojo".equalsIgnoreCase(color);
    }
}

