package serviteca.st.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.ListaChequeo;
import serviteca.st.servicio.IListaChequeoServicio;

import java.util.List;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")

public class ListaCheuqeoControlador {

    @Autowired
    private IListaChequeoServicio listaChequeoServicio;

    @GetMapping
    public List<ListaChequeo> listaChequeo() {
        return listaChequeoServicio.Lista();
    }

    @PostMapping
    public ListaChequeo savelista(@RequestBody ListaChequeo listaChequeo) {
        return listaChequeoServicio.Guardar(listaChequeo);
    }

}
