package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import serviteca.st.excepcion.RecursoNoEncontradoExcepcion;
import serviteca.st.modelo.Dto.InfoServicioDto;
import serviteca.st.modelo.Servicio;
import serviteca.st.servicio.IServicioServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")
public class ServicioControlador {
    private static final Logger logger = LoggerFactory.getLogger(ServicioControlador.class);

    @Autowired
    private IServicioServicio servicioServicio;

    @GetMapping("/servicios")
    @PreAuthorize("hasRole('INVENTARIO') or hasRole('ASISTENTE') or hasRole('TALLER')")
    public List<Servicio> listaServicios() {
        var servicios = servicioServicio.listarServicios();
        servicios.forEach(Servicio -> logger.info(servicios.toString()));
        return servicios;
    }

    @PostMapping("/servicios")
    @PreAuthorize("hasRole('INVENTARIO')")
    public Servicio agregarEmpleado(@RequestBody Servicio servicio) {
        logger.info("Servicio guardado: " + servicio);
        return servicioServicio.guardarServicio(servicio);
    }

    @GetMapping("/servicios/{id}")
    @PreAuthorize("hasRole('INVENTARIO')")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable int id) {
        Servicio servicio = servicioServicio.buscarServicioPorId(id);
        if (servicio == null)
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        return ResponseEntity.ok(servicio);
    }

    @DeleteMapping("/servicios/{id}")
    @PreAuthorize("hasRole('INVENTARIO')")
    public ResponseEntity<Map<String, Boolean>> eliminarServicio(@PathVariable Integer id) {
        Servicio servicio = servicioServicio.buscarServicioPorId(id);
        if (servicio == null)
            throw new RecursoNoEncontradoExcepcion(id + " no encontrado");
        servicioServicio.eliminarServicio(servicio);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
        
    }

    @GetMapping("/servicios/buscar")
    @PreAuthorize("hasRole('INVENTARIO')")
    public List<Servicio> buscarServicios(
            @RequestParam(required = false) Integer idServicio,
            @RequestParam(required = false) String descripcion) {

        if (idServicio != null && descripcion != null) {
            return servicioServicio.buscarPorIdYDescripcion(idServicio, descripcion);
        } else if (idServicio != null) {
            return servicioServicio.buscarPorId(idServicio);
        } else if (descripcion != null) {
            return servicioServicio.buscarPorDescripcion(descripcion);
        } else {
            return servicioServicio.listarServicios();
        }
    }

    @GetMapping("/servicios/nuevo-codigo")
    @PreAuthorize("hasRole('INVENTARIO')")
    public ResponseEntity<String> obtenerNuevoCodigo() {
        String nuevoCodigo = servicioServicio.obtenerNuevoCodigo();
        return ResponseEntity.ok(nuevoCodigo);
    }

    @GetMapping("/consultarInformeServicio")
    public List<InfoServicioDto> buscarInformeServicio(@RequestParam String anio,@RequestParam(required = false) String mes,@RequestParam(required = false) String codigo) {
        return servicioServicio.buscarInformeServicio(anio, mes, codigo);
    }
}
