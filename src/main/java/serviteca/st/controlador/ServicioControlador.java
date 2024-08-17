package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.excepcion.RecursoNoEncontradoExcepcion;
import serviteca.st.modelo.Servicio;
import serviteca.st.servicio.IServicioServicio;
import serviteca.st.servicio.ServicioSerivicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/serviteca
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")

public class ServicioControlador {
    private static final Logger logger= LoggerFactory.getLogger(ServicioControlador.class);

    @Autowired
    private IServicioServicio servicioServicio;

    @GetMapping("/servicios")
    public List<Servicio> listaServicios() {
        var servicios = servicioServicio.listarServicios();
        servicios.forEach(Servicio -> logger.info(servicios.toString()));
        return servicios;
    }

    @PostMapping("/servicios")
    public Servicio agregarEmpleado(@RequestBody Servicio servicio) {
        logger.info("Servicio guardado: " + servicio);
        return servicioServicio.guardarServicio(servicio);
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable int id) {
        Servicio servicio = servicioServicio.buscarServicioPorId(id);
        if(servicio==null)
            throw new RecursoNoEncontradoExcepcion("no se encontro el id: " + id);
        return ResponseEntity.ok(servicio);
    }

    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<Map<String, Boolean>>eliminarServicio(@PathVariable Integer id) {
        Servicio servicio = servicioServicio.buscarServicioPorId(id);
        if (servicio == null)
            throw new RecursoNoEncontradoExcepcion(id + " no encontrado");
        servicioServicio.eliminarServicio(servicio);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
