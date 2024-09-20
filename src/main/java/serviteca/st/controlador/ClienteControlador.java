package serviteca.st.controlador;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.excepcion.RecursoNoEncontradoExcepcion;
import serviteca.st.modelo.Cliente;
import serviteca.st.modelo.Servicio;
import serviteca.st.servicio.IClienteServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")
public class ClienteControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private IClienteServicio clienteServicio;

@GetMapping("/cliente")
    public List<Cliente> obtenerClientes(){
    var clientes = clienteServicio.ListarClientes();
    clientes.forEach((cliente -> logger.info(cliente.toString())));
    return clientes;
}

@PostMapping("/cliente")
    public Cliente agregarEmpleado(@RequestBody Cliente cliente){
    logger.info("Cliente a agregar: " + cliente);
    return clienteServicio.guardarCliente(cliente);
}

@GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente>
obtenerClientePorId(@PathVariable Integer id){
  Cliente cliente = clienteServicio.buscarClientePorId(id);
  if (cliente == null)
      throw new RecursoNoEncontradoExcepcion("no se encontro el id: " + id);
  return ResponseEntity.ok(cliente);
}

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id,
                                                     @RequestBody Cliente clienteRecibido){
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if (cliente == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        cliente.setCedula(clienteRecibido.getCedula());
        cliente.setNombre(clienteRecibido.getNombre());
        cliente.setApellido(clienteRecibido.getApellido());
        cliente.setCorreo(clienteRecibido.getCorreo());
        cliente.setDireccion(clienteRecibido.getDireccion());
        cliente.setTelefono(clienteRecibido.getTelefono());
        cliente.setDepartamento(clienteRecibido.getDepartamento());
        cliente.setCiudad(clienteRecibido.getCiudad());

        clienteServicio.guardarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Integer id) {
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if (cliente == null)
            throw new RecursoNoEncontradoExcepcion(id + " no encontrado");
        clienteServicio.eliminarCliente(cliente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }



}