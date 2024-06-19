package utn.TpInstrumentosBackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.TpInstrumentosBackend.entities.Pedido;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    @Query("SELECT CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0')) AS monthYear, COUNT(p) AS count " +
            "FROM Pedido p " +
            "GROUP BY CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0')) " +
            "ORDER BY CONCAT(YEAR(p.fecha), '-', LPAD(CAST(MONTH(p.fecha) AS string), 2, '0'))")
    List<Object[]> countPedidosByMonthYear();

    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :fechaDesde AND :fechaHasta ORDER BY p.fecha DESC")
    List<Pedido> findPedidosByFechaBetween(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);

}
