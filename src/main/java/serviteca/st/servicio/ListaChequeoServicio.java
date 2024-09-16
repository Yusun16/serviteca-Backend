package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.ListaChequeo;
import serviteca.st.repositorio.ListaChequeoRepositorio;

import java.util.List;

@Service

public class ListaChequeoServicio implements IListaChequeoServicio {

    @Autowired
    private ListaChequeoRepositorio listaChequeoRepositorio;

    @Override
    public List<ListaChequeo> Lista() {
        return listaChequeoRepositorio.findAll();
    }

    @Override
    public ListaChequeo Guardar(ListaChequeo listaChequeo) {
        return listaChequeoRepositorio.save(listaChequeo);
    }
}
