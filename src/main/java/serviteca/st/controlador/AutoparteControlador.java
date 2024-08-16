package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviteca.st.modelo.Autoparte;
import serviteca.st.servicio.AutoparteServicio;
import serviteca.st.servicio.IAutoparteServicio;

import java.util.List;


@RestController
//http://localhost:8080/serviteca
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")

public class AutoparteControlador {
    private static final Logger logger = LoggerFactory.getLogger(AutoparteControlador.class);

    @Autowired
    private IAutoparteServicio autoparteServicio;

    @GetMapping("/autopartes")
    public List<Autoparte> listarAutoparte(){
        var autoparte = autoparteServicio.listarAutoparte();
        autoparte.forEach((Autoparte -> logger.info(autoparte.toString())));
        return autoparte;
    }
}
