package serviteca.st.servicio;

import serviteca.st.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> ListarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public  Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}