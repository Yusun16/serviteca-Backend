package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.AutoparteDto;
import serviteca.st.modelo.OrdenAutoparte;
import serviteca.st.repositorio.OrdenAutoparteRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenAutoparteServicio implements IOrdenAutoparteServicio {

    @Autowired
    private OrdenAutoparteRepositorio ordenAutoparteRepositorio;

    @Override
    public List<OrdenAutoparte> listarOrdenAutoparteServicio() {
        return ordenAutoparteRepositorio.findAll();
    }

    @Override
    public OrdenAutoparte guardarOrdenAutoparteServicio(OrdenAutoparte ordenAutoparte) {
        // Verifica si ya existe un registro con el mismo orden_id y autoparte_id
        Optional<OrdenAutoparte> existente = ordenAutoparteRepositorio
                .findByOrdenIdAndAutoparteId(ordenAutoparte.getOrden().getIdOrden(), ordenAutoparte.getAutoparte().getIdAupartes());

        if (existente.isPresent()) {
            // Actualiza el registro existente
            OrdenAutoparte registroExistente = existente.get();
            registroExistente.setOrden(ordenAutoparte.getOrden());
            registroExistente.setAutoparte(ordenAutoparte.getAutoparte());
            return ordenAutoparteRepositorio.save(registroExistente);
        } else {
            // Guarda un nuevo registro
            return ordenAutoparteRepositorio.save(ordenAutoparte);
        }
    }


    @Override
    public void eliminarOrdenAutoparteServicio(OrdenAutoparte ordenAutoparte) {
        ordenAutoparteRepositorio.delete(ordenAutoparte);
    }

    @Override
    public OrdenAutoparte buscarOrdenAutoparteServicioPorId(Integer idOrdenAutoparteServicio) {
        Optional<OrdenAutoparte> ordenAutoparte = ordenAutoparteRepositorio.findById(idOrdenAutoparteServicio);
        return ordenAutoparte.orElse(null); // Retorna null si no encuentra el objeto
    }

    @Override
    public List<AutoparteDto> buscarAutopartePorOrdenId(Integer ordenId) {
        return ordenAutoparteRepositorio.buscarAutopartePorOrdenId(ordenId);
    }
}
