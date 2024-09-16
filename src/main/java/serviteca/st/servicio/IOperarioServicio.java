package serviteca.st.servicio;

import serviteca.st.modelo.Operario;

import java.util.List;

public interface IOperarioServicio {
    List<Operario> listarOperarios();

    void eliminarOperario(Operario operario);

    String buscarCedula();

    List<Operario> listarOperariosbyparams(String cedula, String correo, String telefono);
}
