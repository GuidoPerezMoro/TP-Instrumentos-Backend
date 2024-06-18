package utn.TpInstrumentosBackend.services;

import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.services.Base.BaseService;

import java.util.Map;

public interface PedidoService extends BaseService<Pedido,Long>{
    Map<String, Long> getPedidosCountByMonthYear();
}
