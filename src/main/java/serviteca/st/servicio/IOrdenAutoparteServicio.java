package serviteca.st.servicio;

import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.OrdenAutoparte;

import java.util.List;

public interface IOrdenAutoparteServicio {
    List<OrdenAutoparte> listarOrdenAutoparteServicio();
    OrdenAutoparte guardarOrdenAutoparteServicio(OrdenAutoparte ordenAutoparte);
    void eliminarOrdenAutoparteServicio(OrdenAutoparte ordenAutoparte);
    OrdenAutoparte buscarOrdenAutoparteServicioPorId(Integer idOrdenAutoparteServicio);
    List<Autoparte> buscarAutopartePorOrdenId(Integer ordenId);
}

