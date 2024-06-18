package utn.TpInstrumentosBackend.controllers;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Instrumento;
import utn.TpInstrumentosBackend.services.Impl.InstrumentoServiceImpl;
import utn.TpInstrumentosBackend.services.InstrumentoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(path = "api/v1/instrumento")
@CrossOrigin("*")
public class InstrumentoController extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl> {
    @Autowired
    private InstrumentoService instrumentoService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<?> generarPDF(@PathVariable Long id) {
        try {
            Instrumento instrumento = instrumentoService.getById(id);

            ByteArrayOutputStream baos = generarPDFDesdeInstrumento(instrumento);

            // Construimos el nombre del archivo PDF
            String nombreArchivo = instrumento.getInstrumento() + ".pdf";

            // Devolvemos el PDF como un array de bytes
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"")
                    .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"No se pudo generar el PDF para el instrumento con ID " + id + "\"}");
        }
    }

    private ByteArrayOutputStream generarPDFDesdeInstrumento(Instrumento instrumento) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Título
            document.add(new Paragraph(instrumento.getInstrumento())
                    .setFontSize(18)
                    .setBold());

            // Sección de 2 columnas 60/40
            // Columna izquierda (imagen)
            Image imagen = null;
            try {
                imagen = new Image(ImageDataFactory.create(new URL(instrumento.getImagen())));
            } catch (Exception e) {
                // Manejo de errores al cargar la imagen
                imagen = new Image(ImageDataFactory.create("default-image.png")); // Imagen por defecto
            }

            // Escalar la imagen para ajustar al 60% del ancho del documento
            imagen.scaleToFit(pdf.getDefaultPageSize().getWidth() * 0.6f, pdf.getDefaultPageSize().getHeight());
            document.add(imagen);


            // Columna derecha (información del instrumento)
            document.add(new Paragraph(String.valueOf(instrumento.getCantidadVendida()))
                    .setFontSize(8));
            document.add(new Paragraph(instrumento.getInstrumento())
                    .setFontSize(16)
                    .setBold());
            document.add(new Paragraph(String.format("$ %.2f", instrumento.getPrecio()))
                    .setFontSize(12)
                    .setBold());
            document.add(new Paragraph("Marca: " + instrumento.getMarca())
                    .setBold());
            document.add(new Paragraph("Modelo: " + instrumento.getModelo())
                    .setBold());
            document.add(new Paragraph("Categoría: " + instrumento.getCategoria().getCategoria())
                    .setFontSize(12)
                    .setItalic());

            // Costo de envío
            String costoEnvio = instrumento.getCostoEnvio().equals("G") ? "Envío gratis" : "$ " + instrumento.getCostoEnvio();
            document.add(new Paragraph("Costo Envío: " + costoEnvio));

            // Descripción
            document.add(new Paragraph("Descripción:"));
            document.add(new Paragraph(instrumento.getDescripcion()));

        } catch (Exception e) {
            throw new IOException("Error al generar el PDF", e);
        }

        return baos;
    }
}
