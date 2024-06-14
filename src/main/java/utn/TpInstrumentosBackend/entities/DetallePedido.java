package utn.TpInstrumentosBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@Table(name = "DetallePedido")
public class DetallePedido extends Base {
    private int cantidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IdInstrumento")
    private Instrumento instrumento;
}
