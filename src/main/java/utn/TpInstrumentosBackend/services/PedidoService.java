package utn.TpInstrumentosBackend.services;

import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.services.Base.BaseService;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Map;

public interface PedidoService extends BaseService<Pedido,Long>{
    Map<String, Long> getPedidosCountByMonthYear();
    public ByteArrayInputStream exportPedidosToExcel(LocalDate fechaDesde, LocalDate fechaHasta) throws Exception;
}
