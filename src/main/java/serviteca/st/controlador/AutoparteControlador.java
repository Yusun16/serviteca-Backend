package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.excepcion.RecursoNoEncontradoExcepcion;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.InfoAutoparteDto;
import serviteca.st.servicio.IAutoparteServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(origins = "http://localhost:3000")
public class AutoparteControlador {
    private static final Logger logger = LoggerFactory.getLogger(AutoparteControlador.class);

    @Autowired
    private IAutoparteServicio autoparteServicio;

    @GetMapping("/autopartes")
    public List<Autoparte> listarAutoparte() {
        var autoparte = autoparteServicio.listarAutoparte();
        autoparte.forEach(ap -> logger.info(ap.toString()));
        return autoparte;
    }

    @PostMapping("/autopartes")
    public ResponseEntity<Autoparte> guardarAutoparte(@RequestBody Autoparte autoparte) {
        Autoparte nuevaAutoparte = autoparteServicio.guardarAutoparte(autoparte);
        return ResponseEntity.ok(nuevaAutoparte);
    }

    //Capturar el id
    @GetMapping("/autopartes/{id}")
    public ResponseEntity<Autoparte> obtenerAutopartePorId(@PathVariable int id) {
        Autoparte autoparte = autoparteServicio.buscarAutopartePorId(id);
        if (autoparte == null)
            throw new RecursoNoEncontradoExcepcion("no se encontro el id: " + id);
        return ResponseEntity.ok(autoparte);
    }

    // Eliminar el id
    @DeleteMapping("/autopartes/{id}")
    public ResponseEntity<Map<String, Boolean>>eliminarAutoparte(@PathVariable int id) {
        Autoparte autoparte = autoparteServicio.buscarAutopartePorId(id);
        if (autoparte == null)
            throw new RecursoNoEncontradoExcepcion("no se encontro el id: " + id);
        autoparteServicio.eliminarAutoparte(autoparte);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Control de buscar. Buscar I
    @GetMapping("/autopartes/buscar")
    public List<Autoparte> buscarAutopartesPorItems(@RequestParam String siigo, @RequestParam String referencia, @RequestParam(required = false) String descripcion) {
        return autoparteServicio.listautopartebyparanst(siigo, referencia, descripcion);
    }

    // Control de buscar. Buscar II
    @GetMapping("/busqueda")
    public String buscarCodigo() {
        return autoparteServicio.buscarCodigo();
    }

    @GetMapping("/consultarInformeAutoparte")
    public List<InfoAutoparteDto> informeAutoparte(@RequestParam String anio,@RequestParam(required = false) String mes,@RequestParam(required = false) String codigo) {
        return autoparteServicio.informeAutoparte(anio, mes, codigo);
    }
}
