package utn.TpInstrumentosBackend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.TpInstrumentosBackend.entities.Usuario;
import utn.TpInstrumentosBackend.repositories.UsuarioRepository;
import utn.TpInstrumentosBackend.services.Base.BaseServiceImpl;
import utn.TpInstrumentosBackend.services.UsuarioService;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario getByUsername(String username) {
        return usuarioRepository.findByUsername(username).get();
    }

    @Override
    public Usuario getByUsernameAndPassword(String username, String password) {
        if(usuarioRepository.existsByUsernameAndPassword(username,password))
            return usuarioRepository.findByUsername(username).get();
        return null;
    }
}
