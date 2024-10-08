package serviteca.st.servicio;

import serviteca.st.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> ListarClientes();

    public Cliente buscarClientePorId(Integer id);

    public  Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

    List<Cliente> listarClientesbyparams(String cedula, String correo, Double telefono );

    String buscarCedula();
}
