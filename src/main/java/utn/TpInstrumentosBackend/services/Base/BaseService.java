package utn.TpInstrumentosBackend.services.Base;

import utn.TpInstrumentosBackend.entities.Base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> getAll() throws Exception;
    public E getById(ID id) throws Exception;
    public E create(E request) throws Exception;
    public E update(E request, ID id) throws Exception;
    public boolean delete(ID id) throws Exception;
}