package com.REGIXLAB.proyectoRegixlab.dto;

public class NotificacionDTO {
    private String mensaje;
    private String color;
    private Long insumoId;

    public NotificacionDTO() {}

    public NotificacionDTO(String mensaje, String color, Long insumoId) {
        this.mensaje = mensaje;
        this.color = color;
        this.insumoId = insumoId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getColor() {
        return color;
    }

    public Long getInsumoId() {
        return insumoId;
    }
}

