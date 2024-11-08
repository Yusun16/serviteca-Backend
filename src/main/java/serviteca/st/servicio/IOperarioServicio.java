package serviteca.st.servicio;

import serviteca.st.modelo.Dto.InfoOperarioDto;
import serviteca.st.modelo.Operario;

import java.util.List;

public interface IOperarioServicio {

    List<InfoOperarioDto> consultarInformeOperario(String anio,String mes, String cedula);
}
