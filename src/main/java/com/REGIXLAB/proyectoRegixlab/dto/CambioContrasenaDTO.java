package com.REGIXLAB.proyectoRegixlab.dto;

public class CambioContrasenaDTO {
    private String contrasenaActual;
    private String nuevaContrasena;
    private String confirmacionContrasena;

    // Getters y Setters (genera estos con tu IDE)
    public String getContrasenaActual() { return contrasenaActual; }

    public void setContrasenaActual(String contrasenaActual) { this.contrasenaActual = contrasenaActual; }

    public String getNuevaContrasena() { return nuevaContrasena; }

    public void setNuevaContrasena(String nuevaContrasena) { this.nuevaContrasena = nuevaContrasena; }

    public String getConfirmacionContrasena() { return confirmacionContrasena; }

    public void setConfirmacionContrasena(String confirmacionContrasena) { this.confirmacionContrasena = confirmacionContrasena; }
}
