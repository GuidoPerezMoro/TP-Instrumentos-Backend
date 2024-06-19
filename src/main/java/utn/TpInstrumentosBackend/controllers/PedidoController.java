package utn.TpInstrumentosBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Categoria;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.entities.PreferenceMP;
import utn.TpInstrumentosBackend.services.Impl.CategoriaServiceImpl;
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

    @PostMapping("create-preference-mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController mercadopagoController = new MercadoPagoController();
        PreferenceMP preference = mercadopagoController.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }

    @GetMapping("/getByMonthYear")
    public ResponseEntity<Map<String, Long>> getPedidosByMonthYear() {
        Map<String, Long> pedidosByMonthYear = pedidoService.getPedidosCountByMonthYear();
        return ResponseEntity.ok(pedidosByMonthYear);
    }

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> exportPedidosToExcel(@RequestParam LocalDate fechaDesde,
                                                                    @RequestParam LocalDate fechaHasta) throws IOException {
        ByteArrayInputStream in = pedidoService.exportPedidosToExcel(fechaDesde, fechaHasta);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Pedidos_" + fechaDesde + "_to_" + fechaHasta + ".xlsx");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(new InputStreamResource(in));
    }
}
