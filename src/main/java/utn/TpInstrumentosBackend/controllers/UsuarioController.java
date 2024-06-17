package utn.TpInstrumentosBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Usuario;
import utn.TpInstrumentosBackend.services.Impl.UsuarioServiceImpl;

@RestController
@RequestMapping(path = "api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/getByUsername")
    public ResponseEntity<?> getById(@RequestParam String username){
        try{
            return ResponseEntity.ok(usuarioService.getByUsername(username));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @GetMapping("/getByUsernameAndPassword")
    public ResponseEntity<?> getByUsernameAndPassword(@RequestParam String username,@RequestParam String password){
        try{
            return ResponseEntity.ok(usuarioService.getByUsernameAndPassword(username, password));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
