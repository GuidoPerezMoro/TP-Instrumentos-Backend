package utn.TpInstrumentosBackend.services.Impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.TpInstrumentosBackend.entities.DetallePedido;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.repositories.BaseRepository;
import utn.TpInstrumentosBackend.repositories.DetallePedidoRepository;
import utn.TpInstrumentosBackend.repositories.PedidoRepository;
import utn.TpInstrumentosBackend.services.Base.BaseServiceImpl;
import utn.TpInstrumentosBackend.services.PedidoService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {

    @Autowired
    protected PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Override
    public Map<String, Long> getPedidosCountByMonthYear() {
        List<Object[]> results = pedidoRepository.countPedidosByMonthYear();

        Map<String, Long> pedidosByMonthYear = new LinkedHashMap<>();

        // Procesar los resultados para construir el mapa de respuesta
        for (Object[] result : results) {
            String monthYear = result[0].toString();
            Long count = (Long) result[1];
            pedidosByMonthYear.put(monthYear, count);
        }

        return pedidosByMonthYear;
    }

    @Override
    public Map<String, Long> countPedidosByInstrumento() {
        List<Object[]> results = detallePedidoRepository.countPedidosByInstrumento();
        Map<String, Long> pedidosByInstrumento = new LinkedHashMap<>();
        for (Object[] result : results) {
            String instrumento = result[0].toString();
            Long count = (Long) result[1];
            pedidosByInstrumento.put(instrumento, count);
        }
        return pedidosByInstrumento;
    }
    public ByteArrayInputStream exportPedidosToExcel(LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        List<Pedido> pedidos = pedidoRepository.findPedidosByFechaBetween(fechaDesde, fechaHasta);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Pedidos");
            Row headerRow = sheet.createRow(0);

            String[] columns = {"Fecha Pedido", "Instrumento", "Marca", "Modelo", "Cantidad", "Precio", "Subtotal"};
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12); // Ajusta el tamaño de la fuente aquí

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

// Crear encabezados con estilo
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }


            int rowNum = 1;
            for (Pedido pedido : pedidos) {
                for (DetallePedido detalle : pedido.getDetallesPedido()) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(pedido.getFecha().toString());
                    row.createCell(1).setCellValue(detalle.getInstrumento().getInstrumento());
                    row.createCell(2).setCellValue(detalle.getInstrumento().getMarca());
                    row.createCell(3).setCellValue(detalle.getInstrumento().getModelo());
                    row.createCell(4).setCellValue(detalle.getCantidad());
                    row.createCell(5).setCellValue(detalle.getInstrumento().getPrecio());
                    row.createCell(6).setCellValue(detalle.getCantidad() * detalle.getInstrumento().getPrecio());
                }
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
