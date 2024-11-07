package serviteca.st.servicio;

import serviteca.st.modelo.Dto.EjecucionServicioDto;
import serviteca.st.modelo.Dto.buscarOrdenEspecificaDto;
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

    public String buscarCodigo();

    public List<vehiculosClientes>  findVehiculosClientes(Integer clienteId);

    public List<EjecucionServicioDto>  findejecucionservicio(Integer idOrden);

    void update(Integer id, Orden object) throws Exception;

    List<buscarOrdenEspecificaDto> buscarOrdenEspecifica(String codigo, String nombreCliente, LocalDate fecha, String placa);
}
