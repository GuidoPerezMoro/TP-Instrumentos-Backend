package utn.TpInstrumentosBackend.repositories;

import org.springframework.stereotype.Repository;
import utn.TpInstrumentosBackend.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long>{
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);

}
