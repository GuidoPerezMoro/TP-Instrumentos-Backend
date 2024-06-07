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
@Table(name = "Instrumento")
public class Instrumento extends Base{
    private String instrumento;
    private String marca;
    private String modelo;
    private String imagen;
    private double precio;
    private String costoEnvio;
    private int cantidadVendida;
    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IdCategoria")
    private Categoria categoria;
}