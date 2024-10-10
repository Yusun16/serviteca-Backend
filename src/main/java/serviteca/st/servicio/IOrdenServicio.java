package serviteca.st.servicio;

import serviteca.st.modelo.Dto.vehiculosClientes;
import serviteca.st.modelo.Orden;
import serviteca.st.modelo.Servicio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IOrdenServicio {

    public List<Orden> listarOrden();

    public Orden buscarOrdenPorId(Integer idOrden);

    public Orden guardarOrden(Orden orden);

    public void eliminarOrden(Orden orden);

    public List<Orden> listordenbyparanst(Integer codigo, String nombreCliente, String placaVehiculo, LocalDate fecha);

    public String buscarCodigo();

    public List<vehiculosClientes>  findVehiculosClientes(Integer clienteId);

}
