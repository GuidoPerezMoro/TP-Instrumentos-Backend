package utn.TpInstrumentosBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import utn.TpInstrumentosBackend.Enum.Rol;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@Table(name = "Usuario")
public class Usuario extends Base {

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
