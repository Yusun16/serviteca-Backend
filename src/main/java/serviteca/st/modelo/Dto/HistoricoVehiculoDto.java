package serviteca.st.modelo.Dto;

import java.time.LocalDate;

public interface HistoricoVehiculoDto {

    Integer getIdOrden();

    LocalDate getFechaOrden();

    String getKilometros();

    String getNombreOperario();

    String getObservacion();


}
