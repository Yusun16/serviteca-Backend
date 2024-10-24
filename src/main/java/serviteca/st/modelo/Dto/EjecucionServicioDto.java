package serviteca.st.modelo.Dto;


import java.time.LocalDate;
import java.time.LocalTime;

public interface EjecucionServicioDto {
    String getPlacaVehiculo();
    String getCodigoOrden();
    LocalDate getFechaOrden();
    LocalTime getHoraOrden();
    String getImgFrontalRevision();
    String getImgBackRevision();
    String  getNombreServicio();
}
