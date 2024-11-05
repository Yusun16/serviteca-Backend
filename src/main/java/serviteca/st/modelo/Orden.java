package serviteca.st.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idOrden;
    String codigo;
    String kilometraje;
    LocalDate fecha;
    LocalTime hora;
    Boolean estado;
    LocalDate fechaFin;
    LocalTime horaFin;
    String observaciones;
    @ManyToOne
    @JoinColumn (name = "vehiculo_id")
    private Vehiculo vehiculo;
    @ManyToOne
    @JoinColumn (name = "servicio_id")
    Servicio servicio;
    @ManyToOne
    Operario operario;
}
