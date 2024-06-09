package utn.TpInstrumentosBackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.TpInstrumentosBackend.controllers.Base.BaseControllerImpl;
import utn.TpInstrumentosBackend.entities.Instrumento;
import utn.TpInstrumentosBackend.services.Impl.InstrumentoServiceImpl;

@RestController
@RequestMapping(path = "api/v1/instrumento")
@CrossOrigin("*")
public class InstrumentoController extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl> {
}
