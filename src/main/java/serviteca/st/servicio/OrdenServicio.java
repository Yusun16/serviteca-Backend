package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Dto.vehiculosClientes;
import serviteca.st.modelo.Orden;
import serviteca.st.modelo.Servicio;
import serviteca.st.repositorio.OrdenRepositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service

public class OrdenServicio implements IOrdenServicio{

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Override
    public List<Orden> listarOrden() {
        return ordenRepositorio.findAll();
    }

    @Override
    public Orden buscarOrdenPorId(Integer idOrden) {
       Orden orden = ordenRepositorio.findById(idOrden).orElse(null);
        return orden;
    }

    @Override
    public Orden guardarOrden(Orden orden) {
        return ordenRepositorio.save(orden);
    }

    @Override
    public void eliminarOrden(Orden orden) {
        ordenRepositorio.delete(orden);

    }

    @Override
    public List<Orden> listordenbyparanst(Integer codigo, String nombreCliente, String placaVehiculo, LocalDate fecha) {
        return ordenRepositorio.findByIdOrdenOrClienteAndPlacaVehiculoOrFecha(codigo, nombreCliente, placaVehiculo, fecha );

    }

    @Override
    public String buscarCodigo() {
        String ultimoCodigo = ordenRepositorio.buscarCodigo();
        // Asumamos que el código es numérico y puede ser convertido a entero.
        // Agregamos uno al último código y lo devolvemos.
        int nuevoCodigo = Integer.parseInt(ultimoCodigo) + 1;
        return String.valueOf(nuevoCodigo);
    }

    @Override
    public List<vehiculosClientes> findVehiculosClientes(Integer clienteId) {
        return ordenRepositorio.findVehiculosClientes(clienteId);
    }

}
