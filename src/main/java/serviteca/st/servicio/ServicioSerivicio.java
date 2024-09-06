package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Servicio;
import serviteca.st.repositorio.ServicioRepositorio;

import java.util.List;

@Service
public class ServicioSerivicio implements IServicioServicio {

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepositorio.findAll();
    }

    @Override
    public Servicio buscarServicioPorId(Integer idServicio) {
        return servicioRepositorio.findById(idServicio).orElse(null);
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Servicio servicio) {
        servicioRepositorio.delete(servicio);
    }

    public List<Servicio> buscarPorIdYDescripcion(Integer idServicio, String descripcion) {
        return servicioRepositorio.findByIdServicioAndDescripcionContaining(idServicio, descripcion);
    }

    public List<Servicio> buscarPorId(Integer idServicio) {
        return servicioRepositorio.findByIdServicio(idServicio);
    }

    public List<Servicio> buscarPorDescripcion(String descripcion) {
        return servicioRepositorio.findByDescripcionContaining(descripcion);
    }

    public String obtenerNuevoCodigo() {
        Servicio ultimoServicio = servicioRepositorio.findTopByOrderByIdServicioDesc().orElse(null);
        if (ultimoServicio != null) {
            int nuevoCodigo = Integer.parseInt(ultimoServicio.getCodigo()) + 1;
            return String.valueOf(nuevoCodigo);
        }
        return "1"; // Retorna "1" si no hay servicios existentes.
    }

}
