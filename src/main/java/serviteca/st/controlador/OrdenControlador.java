package serviteca.st.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.Orden;
import serviteca.st.servicio.IOrdenServicio;
import serviteca.st.servicio.OrdenServicio;

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
    public List<Orden> buscarOrdenPorNombre(@RequestParam(required = false) Integer idOrden, @RequestParam String cliente, @RequestParam String placa, @RequestParam(required = false) Date fecha){
    return ordenServicio.listordenbyparanst(idOrden,cliente,placa,fecha);
    }

    @GetMapping("/generarcodigo")
    public String buscarCodigo(){
        return ordenServicio.buscarCodigo();
    };
}
