package serviteca.st.controlador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviteca.st.modelo.Cliente;
import serviteca.st.servicio.IClienteServicio;

import java.util.List;

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



}
