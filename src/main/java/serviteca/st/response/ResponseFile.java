package serviteca.st.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private String observacionLateralDerecho;
    private String observacionLateralIzquierdo;
    private String observacionFrontal;
    private String observacionPosterior;
    private String observacionIndicadorCombustible;
}