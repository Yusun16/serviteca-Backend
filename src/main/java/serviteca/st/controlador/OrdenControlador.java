package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.Dto.*;
import serviteca.st.modelo.Orden;
import serviteca.st.modelo.Revision;
import serviteca.st.servicio.IOrdenServicio;
import serviteca.st.servicio.OrdenServicio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")


public class OrdenControlador {
    private static final Logger logger = LoggerFactory.getLogger((OrdenControlador.class));

    @Autowired
    private IOrdenServicio ordenServicio;

    @GetMapping("/ordenservicios")
    public List<Orden> obtenerOrdenes(){
        var ordenes = ordenServicio.listarOrden();
        ordenes.forEach((orden -> logger.info(orden.toString())));
        return ordenes;
    }

    @PostMapping("/ordenservicios")
    public Orden agregarOrden(@RequestBody Orden orden){
        logger.info("Orden a agregar: " + orden);
        return ordenServicio.guardarOrden(orden);
    }

    @GetMapping("/buscarorden")
    public List<buscarOrdenEspecificaDto> buscarOrdenEspecifica(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String nombreCliente,
            @RequestParam(required = false) String placaVehiculo,
            @RequestParam(required = false) LocalDate fecha
            ){
    return ordenServicio.buscarOrdenEspecifica(codigo,nombreCliente,fecha, placaVehiculo);
    }

    @GetMapping("/generarcodigo")
    public String buscarCodigo(){
        return ordenServicio.buscarCodigo();
    };

    @GetMapping("/vehiculosCliente/{clienteId}")
    public List<vehiculosClientes> findVehiculosClientes(@PathVariable Integer clienteId) {
        return ordenServicio.findVehiculosClientes(clienteId);
    }

    @GetMapping("/ejecucion")
    public List<EjecucionServicioDto> findejecucionservicio(Integer idOrden) {
        return ordenServicio.findejecucionservicio(idOrden);
    }

    @PutMapping("/orden/{id}")
    public ResponseEntity<ApiResponseDto<Revision>> update(@PathVariable Integer id, @RequestBody Orden
            object) {
        try {
            ordenServicio.update(id, object);
            return ResponseEntity.ok(new ApiResponseDto<>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Revision>(e.getMessage(), null, false));
        }
    }

    @GetMapping("/historicoVehiculo")
    public List<HistoricoVehiculoDto> consultarHistoricoVehiculo(@RequestParam Integer vehiculoId) {
        return ordenServicio.consultarHistoricoVehiculo(vehiculoId);
    }
}
