package com.REGIXLAB.proyectoRegixlab.util;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class PdfExportadorInsumos {

    private List<Insumo> listaInsumos;

    public PdfExportadorInsumos(List<Insumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    private void escribirCabeceraTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Cantidad", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Estado", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha Registro", fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla) {
        for (Insumo insumo : listaInsumos) {
            tabla.addCell(String.valueOf(insumo.getIdInsumo()));
            tabla.addCell(insumo.getNombre());
            tabla.addCell(String.valueOf(insumo.getCantidad()));
            tabla.addCell(insumo.getEstadoSemaforizacion());
            tabla.addCell(insumo.getFechaRegistro() != null ? insumo.getFechaRegistro().toString() : "");
        }
    }

    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTitulo.setSize(18);
        fuenteTitulo.setColor(Color.BLUE);

        Paragraph titulo = new Paragraph("Listado de Insumos", fuenteTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(20);

        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1f, 3f, 2f, 2f, 3f});
        tabla.setSpacingBefore(10);

        escribirCabeceraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}
