package serviteca.st.servicio;

import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.InfoAutoparteDto;

import java.util.List;

public interface IAutoparteServicio {
    List<Autoparte> listarAutoparte();
    Autoparte guardarAutoparte(Autoparte autoparte);
    void eliminarAutoparte(Autoparte autoparte);
    Autoparte buscarAutopartePorId(Integer idAutoparte);

    String buscarCodigo();
    List<Autoparte> listautopartebyparanst(String siigo, String referencia, String descripcion);

    List<InfoAutoparteDto> informeAutoparte(String anio, String mes, String codigo);
}
