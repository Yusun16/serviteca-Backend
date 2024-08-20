package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Servicio;
import serviteca.st.repositorio.ServicioRepositorio;

import java.util.List;

@Service

public class ServicioSerivicio implements IServicioServicio{

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepositorio.findAll();
    }

    @Override
    public Servicio buscarServicioPorId(Integer idServicio) {
        Servicio servicio= servicioRepositorio.findById(idServicio).orElse(null);
        return servicio;
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Servicio servicio) {
        servicioRepositorio.delete(servicio);
    }
}
