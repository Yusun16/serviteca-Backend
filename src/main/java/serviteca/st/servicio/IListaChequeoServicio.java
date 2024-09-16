package serviteca.st.servicio;

import serviteca.st.modelo.ListaChequeo;

import java.util.List;

public interface IListaChequeoServicio {

    List<ListaChequeo> Lista();
    ListaChequeo Guardar (ListaChequeo listaChequeo);
}
