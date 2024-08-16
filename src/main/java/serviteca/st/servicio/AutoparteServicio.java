package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Autoparte;
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
    public Autoparte buscarAutopartePorId(Integer idAutopartes) {
        Autoparte autoparte = autopoartesRepositorio.findById(idAutopartes).orElse(null);
        return autoparte;
    }

    @Override
    public Autoparte guardarAutoparte(Autoparte autoparte) {
        return autopoartesRepositorio.save(autoparte);
    }

    @Override
    public void eliminarAutoparte(Autoparte autoparte) {
        autopoartesRepositorio.delete(autoparte);

    }
}
