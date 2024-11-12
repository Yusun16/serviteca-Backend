package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.AutoparteDto;
import serviteca.st.modelo.OrdenAutoparte;
import serviteca.st.repositorio.AutopoartesRepositorio;
import serviteca.st.repositorio.OrdenAutoparteRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenAutoparteServicio implements IOrdenAutoparteServicio {

    @Autowired
    private OrdenAutoparteRepositorio ordenAutoparteRepositorio;
    @Autowired
    private AutopoartesRepositorio autopoartesRepositorio;

    @Override
    public List<OrdenAutoparte> listarOrdenAutoparteServicio() {
        return ordenAutoparteRepositorio.findAll();
    }

    @Override
    public OrdenAutoparte guardarOrdenAutoparteServicio(OrdenAutoparte ordenAutoparte) {
        Optional<OrdenAutoparte> existente = ordenAutoparteRepositorio
                .findByOrdenIdAndAutoparteId(ordenAutoparte.getOrden().getIdOrden(), ordenAutoparte.getAutoparte().getIdAupartes());

        Autoparte autoparte = autopoartesRepositorio.findById(ordenAutoparte.getAutoparte().getIdAupartes())
                .orElseThrow(() -> new IllegalArgumentException("La autoparte no existe en el inventario"));

        int cantidadSolicitada = ordenAutoparte.getCantidad();

        if (existente.isPresent()) {
            OrdenAutoparte registroExistente = existente.get();

            int diferenciaCantidad = cantidadSolicitada - registroExistente.getCantidad();
            int nuevaCantidadInventario = autoparte.getCantidad() - diferenciaCantidad;

            if (nuevaCantidadInventario < 0) {
                throw new IllegalArgumentException("No hay suficiente inventario de autoparte.");
            }

            autoparte.setCantidad(nuevaCantidadInventario);
            autopoartesRepositorio.save(autoparte);

            registroExistente.setCantidad(cantidadSolicitada);
            registroExistente.setOrden(ordenAutoparte.getOrden());
            registroExistente.setAutoparte(autoparte);

            return ordenAutoparteRepositorio.save(registroExistente);
        } else {
            int nuevaCantidadInventario = autoparte.getCantidad() - cantidadSolicitada;

            if (nuevaCantidadInventario < 0) {
                throw new IllegalArgumentException("No hay suficiente inventario de autoparte.");
            }

            autoparte.setCantidad(nuevaCantidadInventario);
            autopoartesRepositorio.save(autoparte);

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
        return ordenAutoparte.orElse(null);
    }

    @Override
    public List<AutoparteDto> buscarAutopartePorOrdenId(Integer ordenId) {
        return ordenAutoparteRepositorio.buscarAutopartePorOrdenId(ordenId);
    }
}
