package serviteca.st.servicio;

import serviteca.st.modelo.Orden;
import serviteca.st.modelo.Servicio;

import java.util.List;

public interface IOrdenServicio {

    public List<Orden> listarOrden();

    public Orden buscarOrdenPorId(Integer idOrden);

    public Orden guardarOrden(Orden orden);

    public void eliminarOrden(Orden orden);

}
