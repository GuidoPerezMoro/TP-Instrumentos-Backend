package utn.TpInstrumentosBackend.controllers.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.TpInstrumentosBackend.entities.Base;
import utn.TpInstrumentosBackend.services.Base.BaseServiceImpl;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E,Long>> implements BaseController<E,Long> {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImpl.class);
    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            logger.info("Inicio método getAll");
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (Exception e){
            logger.info("Ocurrió un error en el método getAll");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            logger.info("Inicio método getById {}",id);
            return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        } catch (Exception e){
            logger.info("Ocurrió un error en el método getById {}",id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @PostMapping
    public  ResponseEntity<?> create(@RequestBody E entity){
        try{
            logger.info("Inicio método create {}", entity.getClass());
            return ResponseEntity.status(HttpStatus.OK).body(service.create(entity));
        } catch (Exception e){
            logger.info("Ocurrió un error en el método create {}", entity.getClass());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity){
        try{
            logger.info("Inicio método update {}", entity.getClass());
            return ResponseEntity.status(HttpStatus.OK).body(service.update(entity, id));
        } catch (Exception e){
            logger.info("Ocurrió un error en el método update {}", entity.getClass());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            logger.info("Inicio método delete elemento id {}", id);
            if (service.delete(id))
                return ResponseEntity.status(HttpStatus.OK).body("{\"Elemento borrado correctamente\"}");
            else
                throw new RuntimeException();
        } catch (Exception e){
            logger.info("Ocurrió un error en el método delete elemento id {}",id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
