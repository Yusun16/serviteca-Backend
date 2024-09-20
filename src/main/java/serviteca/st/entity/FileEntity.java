package serviteca.st.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String type;
    @Lob
    private byte[] data;

    // Nuevos campos para observaciones
    private String observacionLateralDerecho;
    private String observacionLateralIzquierdo;
    private String observacionFrontal;
    private String observacionPosterior;
    private String observacionIndicadorCombustible;
}