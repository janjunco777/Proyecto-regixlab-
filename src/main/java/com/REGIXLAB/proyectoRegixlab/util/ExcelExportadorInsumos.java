package com.REGIXLAB.proyectoRegixlab.util;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportadorInsumos {

    public ExcelExportadorInsumos(List<Insumo> insumosFiltrados) {
    }

    public static ByteArrayInputStream insumosAExcel(List<Insumo> insumos) throws IOException {
        return null;
    }

    public void exportar(HttpServletResponse response) {
    }
}
