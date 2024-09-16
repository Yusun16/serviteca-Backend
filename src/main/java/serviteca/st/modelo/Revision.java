package serviteca.st.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
@Table(name = "revision")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String imgFrontal;
    String imgBack;
    String imgRight;
    String imgLeft;
    String imgIndicador;
    String observationsFrontal;
    String observationsBack;
    String observationsRight;
    String observationsLeft;
    String observationsIndicador;
}
