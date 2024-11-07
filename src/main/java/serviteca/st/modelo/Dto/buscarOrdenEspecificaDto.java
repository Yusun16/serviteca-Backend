package serviteca.st.modelo.Dto;

import java.time.LocalDate;

public interface buscarOrdenEspecificaDto {

    String getIdOrden();

    LocalDate getFecha();

    String getKilometraje();

    String getCodigo();

    String getObservaciones();

    String getNombreOperario();

}
