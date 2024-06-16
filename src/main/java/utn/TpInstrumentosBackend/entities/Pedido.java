package utn.TpInstrumentosBackend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@Table(name = "Pedido")
public class Pedido extends Base {
    private LocalDate fecha;
    private double totalPedido;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IdDetallePedido")
    private DetallePedido detalle;
}
