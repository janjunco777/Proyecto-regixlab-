package com.REGIXLAB.proyectoRegixlab.dto;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;

import java.util.List;

public class SemaforizacionDTO {

    private String color;
    private List<Insumo> insumos;
    private int totalInsumos;
    private double porcentaje; // respecto al total general

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

    // Lógica adicional (ejemplo: alerta si está vacío)
    public boolean necesitaAtencion() {
        return totalInsumos == 0 && color.equals("rojo");
    }
}
