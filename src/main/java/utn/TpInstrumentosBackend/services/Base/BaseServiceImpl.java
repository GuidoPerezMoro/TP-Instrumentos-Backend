package utn.TpInstrumentosBackend.services.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utn.TpInstrumentosBackend.entities.Base;
import utn.TpInstrumentosBackend.repositories.BaseRepository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E,ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseRepository<E,ID> baseRepository;

    @Override
    @Transactional
    public List<E> getAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            List<E> entitiesActive = new java.util.ArrayList<>(Collections.emptyList());
            for (E entity: entities) {
                if (entity.isActive())
                    entitiesActive.add(entity);
            }
            logger.info("Obtenidas entidades {}", entitiesActive);
            return entitiesActive;
        } catch (Exception e) {
            logger.info("No se hallaron las entidades solicitadas ");
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public E getById(ID id) {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (entityOptional.isPresent() && entityOptional.get().isActive()) {
            logger.info("Obtenida entidad {}", entityOptional);
            return entityOptional.get();
        }
        else {
            logger.error("No se encontró una entidad con el id " + id);
            throw new RuntimeException("No se encontró una entidad con el id " + id);
        }
    }

    @Override
    @Transactional
    public E create(E request) throws Exception {
        try {
            E newEntity = baseRepository.save(request);
            logger.info("Creada entidad {}", newEntity);
            return newEntity;
        } catch (Exception e){
            logger.info("Ocurrió un error, no se pudo crear la entidad");
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(E request, ID id) {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (entityOptional.isPresent() && entityOptional.get().isActive()) {
            E entityUpdate = baseRepository.save(request);
            logger.info("Actualizada entidad {}", entityUpdate);
            return entityUpdate;
        }
        else {
            logger.error("No se encontró una entidad con el id " + request.getId());
            throw new RuntimeException("No se encontró una entidad con el id " + request.getId());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (entityOptional.isPresent() && entityOptional.get().isActive()) {
            E entityUpdate = entityOptional.get();
            entityUpdate.setActive(false);
            baseRepository.save(entityUpdate);
            logger.info("Borrada lógicamente entidad {}", entityUpdate);
            return true;
        }
        else {
            logger.error("No se encontró una entidad con el id " + id);
            throw new RuntimeException("No se encontró una entidad con el id " + id);
        }
    }
}
