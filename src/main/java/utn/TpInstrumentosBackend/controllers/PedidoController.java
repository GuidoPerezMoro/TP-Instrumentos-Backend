package utn.TpInstrumentosBackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Categoria;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.services.Impl.CategoriaServiceImpl;
import utn.TpInstrumentosBackend.services.Impl.PedidoServiceImpl;

@RestController
@RequestMapping(path = "api/v1/pedido")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {
}
