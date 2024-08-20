package serviteca.st.servicio;

import serviteca.st.modelo.Servicio;

import java.util.List;

public interface IServicioServicio {
    public List<Servicio> listarServicios();

    public Servicio buscarServicioPorId(Integer idServicio);

    public Servicio guardarServicio(Servicio servicio);

    public void eliminarServicio(Servicio servicio);
}
