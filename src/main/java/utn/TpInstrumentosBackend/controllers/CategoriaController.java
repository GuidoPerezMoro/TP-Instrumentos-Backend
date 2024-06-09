package utn.TpInstrumentosBackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Categoria;
import utn.TpInstrumentosBackend.services.Impl.CategoriaServiceImpl;

@RestController
@RequestMapping(path = "api/v1/categoria")
@CrossOrigin("*")
public class CategoriaController extends BaseControllerImpl<Categoria, CategoriaServiceImpl> {
}
