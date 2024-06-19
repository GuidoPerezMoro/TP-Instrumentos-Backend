package utn.TpInstrumentosBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.*;
import utn.TpInstrumentosBackend.services.Impl.CategoriaServiceImpl;
import utn.TpInstrumentosBackend.services.Impl.InstrumentoServiceImpl;
import utn.TpInstrumentosBackend.services.Impl.PedidoServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/pedido")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    @Autowired
    protected PedidoServiceImpl pedidoService;
    @Autowired
    private InstrumentoServiceImpl instrumentoService;

    @PostMapping("create-preference-mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController mercadopagoController = new MercadoPagoController();
        PreferenceMP preference = mercadopagoController.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pedido pedido) {
        try {
            // Crear el pedido
            Pedido nuevoPedido = pedidoService.create(pedido);

            // Actualizar la cantidad vendida de los instrumentos
            for (DetallePedido detalle : pedido.getDetallesPedido()) {
                Instrumento instrumento = detalle.getInstrumento();
                instrumento.setCantidadVendida(instrumento.getCantidadVendida() + detalle.getCantidad());
                instrumentoService.update(instrumento, instrumento.getId());
            }

            return ResponseEntity.status(HttpStatus.OK).body(nuevoPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @GetMapping("/getByMonthYear")
    public ResponseEntity<Map<String, Long>> getPedidosByMonthYear() {
        Map<String, Long> pedidosByMonthYear = pedidoService.getPedidosCountByMonthYear();
        return ResponseEntity.ok(pedidosByMonthYear);
    }

    @GetMapping("/countByInstrumento")
    public ResponseEntity<Map<String, Long>> countPedidosByInstrumento() {
        Map<String, Long> pedidosByInstrumento = pedidoService.countPedidosByInstrumento();
        return ResponseEntity.ok(pedidosByInstrumento);
    }

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> exportPedidosToExcel(@RequestParam LocalDate fechaDesde, @RequestParam LocalDate fechaHasta) throws IOException {
        ByteArrayInputStream in = pedidoService.exportPedidosToExcel(fechaDesde, fechaHasta);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Pedidos_" + fechaDesde + "_to_" + fechaHasta + ".xlsx");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(new InputStreamResource(in));
    }

}
