package serviteca.st.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.OrdenAutoparte;
import serviteca.st.servicio.IOrdenAutoparteServicio;

import java.util.List;

@RestController
@RequestMapping("/api/orden-autoparte")
public class OrdenAutoparteControlador {

    @Autowired
    private IOrdenAutoparteServicio ordenAutoparteServicio;

    // Obtener todos los registros de OrdenAutoparte
    @GetMapping
    public ResponseEntity<List<OrdenAutoparte>> listarOrdenAutoparte() {
        List<OrdenAutoparte> ordenesAutoparte = ordenAutoparteServicio.listarOrdenAutoparteServicio();
        return new ResponseEntity<>(ordenesAutoparte, HttpStatus.OK);
    }

    // Obtener un registro de OrdenAutoparte por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenAutoparte> obtenerOrdenAutopartePorId(@PathVariable Integer id) {
        OrdenAutoparte ordenAutoparte = ordenAutoparteServicio.buscarOrdenAutoparteServicioPorId(id);
        if (ordenAutoparte != null) {
            return new ResponseEntity<>(ordenAutoparte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo registro de OrdenAutoparte
    @PostMapping
    public ResponseEntity<OrdenAutoparte> guardarOrdenAutoparte(@RequestBody OrdenAutoparte ordenAutoparte) {
        OrdenAutoparte nuevaOrdenAutoparte = ordenAutoparteServicio.guardarOrdenAutoparteServicio(ordenAutoparte);
        return new ResponseEntity<>(nuevaOrdenAutoparte, HttpStatus.CREATED);
    }

    // Eliminar un registro de OrdenAutoparte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrdenAutoparte(@PathVariable Integer id) {
        OrdenAutoparte ordenAutoparte = ordenAutoparteServicio.buscarOrdenAutoparteServicioPorId(id);
        if (ordenAutoparte != null) {
            ordenAutoparteServicio.eliminarOrdenAutoparteServicio(ordenAutoparte);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/consultarAutopartePorId")
    public List<Autoparte> buscarAutopartePorOrdenId(@RequestParam Integer ordenId) {
        return ordenAutoparteServicio.buscarAutopartePorOrdenId(ordenId);
    }
}
