package serviteca.st.servicio;

import serviteca.st.modelo.Servicio;

import java.util.List;

public interface IServicioServicio {
    public List<Servicio> listarServicios();

    public Servicio buscarServicioPorId(Integer idServicio);

    public Servicio guardarServicio(Servicio servicio);

    public void eliminarServicio(Servicio servicio);

    List<Servicio> buscarPorIdYDescripcion(Integer idServicio, String descripcion);

    List<Servicio> buscarPorId(Integer idServicio);

    List<Servicio> buscarPorDescripcion(String descripcion);

    String obtenerNuevoCodigo();
}
