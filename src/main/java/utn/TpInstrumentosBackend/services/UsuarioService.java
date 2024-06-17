package utn.TpInstrumentosBackend.services;

import utn.TpInstrumentosBackend.entities.Usuario;
import utn.TpInstrumentosBackend.services.Base.BaseService;

import java.util.Optional;

public interface UsuarioService extends BaseService<Usuario,Long>{
    Usuario getByUsername(String username);
    Usuario getByUsernameAndPassword(String username, String password);
}
