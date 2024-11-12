package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Cliente;
import serviteca.st.repositorio.ClienteRepositorio;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> ListarClientes() {

        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
        return cliente;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }

    @Override
    public List<Cliente> listarClientesbyparams(String cedula, String correo, Double telefono) {
        return clienteRepositorio.findByCedulaOrCorreoOrTelefono(cedula,correo,telefono);
    }

    public String buscarCedula() {
        String searchCedula = clienteRepositorio.buscarCedula();
        return searchCedula;
    }


}
