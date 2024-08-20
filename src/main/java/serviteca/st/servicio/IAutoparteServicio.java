package serviteca.st.servicio;

import serviteca.st.modelo.Autoparte;

import java.util.List;

public interface IAutoparteServicio {
    public List<Autoparte> listarAutoparte();

    public Autoparte buscarAutopartePorId(Integer idAutoparte);

    public Autoparte guardarAutoparte(Autoparte autoparte);

    public void eliminarAutoparte(Autoparte autoparte);
}
