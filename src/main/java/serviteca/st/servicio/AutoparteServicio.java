package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.InfoAutoparteDto;
import serviteca.st.repositorio.AutopoartesRepositorio;

import java.util.List;

@Service
public class AutoparteServicio implements IAutoparteServicio {

    @Autowired
    private AutopoartesRepositorio autopoartesRepositorio;

    @Override
    public List<Autoparte> listarAutoparte() {
        return autopoartesRepositorio.findAll();
    }

    @Override
    public Autoparte buscarAutopartePorId(Integer idAutoparte) {
        // Se hizo unos cambios!!!
        Autoparte autoparte = autopoartesRepositorio.findById(idAutoparte).orElse(null);
        return autoparte;
    }

    //Guardar = Crear
    @Override
    public Autoparte guardarAutoparte(Autoparte autoparte) {
        return autopoartesRepositorio.save(autoparte);
    }

    @Override
    public void eliminarAutoparte(Autoparte autoparte) {
        autopoartesRepositorio.delete(autoparte);
    }

    @Override
    public List<Autoparte> listautopartebyparanst(String siigo, String referencia, String descripcion) {
        return autopoartesRepositorio.findBySiigoOrReferenciaOrDescripcion(siigo, referencia, descripcion);
    }

    @Override
    public List<InfoAutoparteDto> informeAutoparte(String anio, String mes, String codigo) {
        return autopoartesRepositorio.informeAutoparte(anio, mes, codigo);
    }

    @Override
    public String buscarCodigo() {
        String searchCodigo = autopoartesRepositorio.buscarCodigo();
        int nuevoCodigo = Integer.parseInt(searchCodigo);
        return String.valueOf(nuevoCodigo);
    }
}
