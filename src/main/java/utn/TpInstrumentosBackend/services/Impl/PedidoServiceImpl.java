package utn.TpInstrumentosBackend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.repositories.BaseRepository;
import utn.TpInstrumentosBackend.repositories.PedidoRepository;
import utn.TpInstrumentosBackend.services.Base.BaseServiceImpl;
import utn.TpInstrumentosBackend.services.PedidoService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {

    @Autowired
    protected PedidoRepository pedidoRepository;
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
}
