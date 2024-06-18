package utn.TpInstrumentosBackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.TpInstrumentosBackend.entities.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{
    @Query("SELECT CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0')) AS monthYear, COUNT(p) AS count " +
            "FROM Pedido p " +
            "GROUP BY CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0')) " +
            "ORDER BY CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0'))")
    List<Object[]> countPedidosByMonthYear();
}
