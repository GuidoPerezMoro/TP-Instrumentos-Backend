package utn.TpInstrumentosBackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.TpInstrumentosBackend.entities.DetallePedido;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long>{
    @Query("SELECT d.instrumento.instrumento, COUNT(d) FROM DetallePedido d GROUP BY d.instrumento.instrumento")
    List<Object[]> countPedidosByInstrumento();
}
