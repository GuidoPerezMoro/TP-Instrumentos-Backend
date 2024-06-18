package utn.TpInstrumentosBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Categoria;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.entities.PreferenceMP;
import utn.TpInstrumentosBackend.services.Impl.CategoriaServiceImpl;
import utn.TpInstrumentosBackend.services.Impl.PedidoServiceImpl;

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
}
