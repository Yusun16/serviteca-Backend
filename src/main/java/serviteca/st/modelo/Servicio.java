package serviteca.st.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idServicio;
    String codigo;
    String descripcion;
    Double valorServicio;
    String ano;
    String porcentajeOperario;
    @ManyToOne
    Operario operario;

    
}
