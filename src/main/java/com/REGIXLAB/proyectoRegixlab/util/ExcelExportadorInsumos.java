package com.REGIXLAB.proyectoRegixlab.util;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportadorInsumos {

    public static ByteArrayInputStream insumosAExcel(List<Insumo> insumos) throws IOException {
        String[] columnas = {"ID", "Nombre", "Registro INVIMA", "Cantidad", "Lugar", "Semaforización"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet hoja = workbook.createSheet("Insumos");

            // Encabezado
            Row filaHeader = hoja.createRow(0);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = filaHeader.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // Datos
            int filaIdx = 1;
            for (Insumo insumo : insumos) {
                Row fila = hoja.createRow(filaIdx++);
                fila.createCell(0).setCellValue(insumo.getIdInsumo());
                fila.createCell(1).setCellValue(insumo.getNombre());
                fila.createCell(2).setCellValue(insumo.getRegistroInvima());
                fila.createCell(3).setCellValue(insumo.getCantidad());
                fila.createCell(4).setCellValue(insumo.getLugarAlmacenamiento());
                fila.createCell(5).setCellValue(insumo.getEstadoSemaforizacion());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
