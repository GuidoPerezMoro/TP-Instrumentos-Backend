package utn.TpInstrumentosBackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.DetallePedido;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.services.Impl.DetallePedidoServiceImpl;
import utn.TpInstrumentosBackend.services.Impl.PedidoServiceImpl;

@RestController
@RequestMapping(path = "api/v1/detalle-pedido")
@CrossOrigin("*")
public class DetallePedidoController extends BaseControllerImpl<DetallePedido, DetallePedidoServiceImpl> {
}
